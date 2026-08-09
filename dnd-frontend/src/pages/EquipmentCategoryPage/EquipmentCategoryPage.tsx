import { useEffect, useMemo, useState } from "react";
import { PageTitle } from "../../shared/ui/PageTitle";
import { CategoryCard } from "../../shared/ui/CategoryCard/CategoryCard";
import { PageBackground } from "../../shared/ui/PageBackground/PageBackground";
import { Pagination } from "../../shared/ui/Pagination/Pagination";
import { useSearchStore } from "../../shared/store/searchStore";
import { API_BASE_URL } from "../../shared/api/config";
import { getFavoriteUniqueId, getEntityTypeFromPath } from "../../shared/utils/favoritesUtils";
import { ROWS_PER_PAGE, SIZE_BREAKPOINTS, GRID_CALC } from "../AsyncCategoryPage/constants/gridConfig";
import { normalizeText, buildItemSearchText } from "../AsyncCategoryPage/utils/searchUtils";
import { toApiItems } from "../AsyncCategoryPage/utils/apiItemUtils";
import { filterEquipmentByCategory } from "./filterEquipment";
import "./EquipmentCategoryPage.scss";
import type { ApiListItem, ApiListResponse, AsyncListItem } from "../AsyncCategoryPage/types/api";

interface EquipmentCategoryPageProps {
  title: string;
  endpoint: string;
  categoriesEndpoint: string;
  basePath: string;
  equipmentType: "weaponCategory" | "armorCategory" | "gearCategory";
  backgroundVariant?: "signup" | "login" | "account" | "favorites";
}

interface EquipmentCategory {
  originalIndex: string;
  name: string;
  itemIndexes: Set<string>;
}

const computeColumnsFromContainer = () => {
  if (typeof window === 'undefined') return 2;
  
  const container = document.querySelector('.main-layout') as HTMLElement | null;
  const containerWidth = container ? container.clientWidth : window.innerWidth;

  const isMobile = containerWidth <= SIZE_BREAKPOINTS.mobile;
  const cardWidth = isMobile ? SIZE_BREAKPOINTS.mobilCardWidth : SIZE_BREAKPOINTS.desktopCardWidth;
  const gap = isMobile ? SIZE_BREAKPOINTS.mobileGap : SIZE_BREAKPOINTS.desktopGap;

  for (let cols = GRID_CALC.maxColumns; cols >= GRID_CALC.minColumns; cols--) {
    const required = cols * cardWidth + (cols - 1) * gap;
    if (required <= containerWidth) return cols;
  }

  return GRID_CALC.minColumns;
};

export const EquipmentCategoryPage = ({
  title,
  endpoint,
  categoriesEndpoint,
  basePath,
  equipmentType,
  backgroundVariant,
}: EquipmentCategoryPageProps) => {
  const [items, setItems] = useState<AsyncListItem[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [columns, setColumns] = useState(computeColumnsFromContainer());
  const [selectedCategory, setSelectedCategory] = useState<string | null>(null);
  const [categories, setCategories] = useState<EquipmentCategory[]>([]);
  const [categoryItemIndexes, setCategoryItemIndexes] = useState<Map<string, Set<string>>>(new Map());
  const [categoriesLoading, setCategoriesLoading] = useState(true);

  const searchQuery = useSearchStore((state) => state.query);

  const itemsPerPage = useMemo(() => ROWS_PER_PAGE * columns, [columns]);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        setCategoriesLoading(true);
        let page = 0;
        let hasMore = true;
        const allCategories: ApiListItem[] = [];

        while (hasMore) {
          const response = await fetch(`${API_BASE_URL}${categoriesEndpoint}?size=50&page=${page}`);
          if (!response.ok) throw new Error("Failed to fetch categories");
          const data = (await response.json()) as ApiListResponse;
          const pageItems = toApiItems(data);
          allCategories.push(...pageItems);
          hasMore = !data.last;
          page++;
        }

        const indexesMap = new Map<string, Set<string>>();
        const mapped: EquipmentCategory[] = allCategories.map((item: ApiListItem) => {
          const catIndex = (item.index ?? item.originalIndex ?? "") as string;
          const equipmentList = Array.isArray(item.equipment) ? item.equipment : [];
          const itemIndexSet = new Set<string>(
            equipmentList
              .map((e: any) => e.index as string)
              .filter(Boolean)
          );
          indexesMap.set(catIndex, itemIndexSet);
          return { originalIndex: catIndex, name: item.name || "", itemIndexes: itemIndexSet };
        });

        setCategories(mapped);
        setCategoryItemIndexes(indexesMap);
      } catch (err) {
        console.error("Error fetching categories:", err);
      } finally {
        setCategoriesLoading(false);
      }
    };

    fetchCategories();
  }, [categoriesEndpoint]);

  useEffect(() => {
    const fetchItems = async () => {
      try {
        setIsLoading(true);

        const formatItem = (item: ApiListItem, pathId: string) => {
          let imagePath = item.image || item.imageUrl || item.icon || "";
          if (typeof imagePath === "string" && imagePath.startsWith("/api/images/")) {
            imagePath = `https://www.dnd5eapi.co${imagePath}`;
          }
          const entityType = getEntityTypeFromPath(basePath) || pathId || "";
          const uniqueId = pathId ? getFavoriteUniqueId(entityType, pathId) : Math.random().toString();
          return {
            id: uniqueId,
            title: item.name || item.title || "Unknown",
            path: `${basePath}/${pathId}`,
            icon: imagePath,
            searchText: buildItemSearchText(item),
            itemIndex: pathId,
            weaponCategory: (item as any).weapon_category,
            weaponRange: (item as any).weapon_range,
            categoryRange: (item as any).category_range,
            armorCategory: (item as any).armor_category,
            gearCategory: (item as any).gear_category,
            equipmentCategoryIndex: (item as any).equipment_category?.index,
          };
        };

        const getPathId = (item: ApiListItem): string => {
          const rawPathId = item.index ?? item.id;
          if (typeof rawPathId === "string" || typeof rawPathId === "number") return String(rawPathId);
          if (typeof item.url === "string") {
            const urlParts = item.url.split("/").filter(Boolean);
            return urlParts[urlParts.length - 1] || "";
          }
          return "";
        };

        if (equipmentType === "gearCategory") {
          const allCatPages: ApiListItem[] = [];
          let page = 0;
          let hasMore = true;
          while (hasMore) {
            const response = await fetch(`${API_BASE_URL}${categoriesEndpoint}?size=50&page=${page}`);
            if (!response.ok) throw new Error("Failed to fetch");
            const data = (await response.json()) as ApiListResponse;
            allCatPages.push(...(toApiItems(data)));
            hasMore = !data.last;
            page++;
          }

          const seen = new Set<string>();
          const formattedItems: typeof items = [];
          for (const cat of allCatPages) {
            const equipmentList = Array.isArray(cat.equipment) ? cat.equipment : [];
            for (const e of equipmentList) {
              const idx = (e as any).index as string;
              if (!idx || seen.has(idx)) continue;
              seen.add(idx);
              formattedItems.push(formatItem(e as ApiListItem, idx));
            }
          }

          setItems(formattedItems);
          setCurrentPage(1);
          return;
        }

        const allData: ApiListItem[] = [];
        let page = 0;
        let hasMore = true;

        while (hasMore) {
          const pageUrl = `${API_BASE_URL}${endpoint}?page=${page}&size=200`;
          const response = await fetch(pageUrl);
          if (!response.ok) throw new Error("Failed to fetch items");
          const pagePayload = (await response.json()) as ApiListResponse | ApiListItem[];
          const pageData = toApiItems(pagePayload);
          allData.push(...pageData);
          hasMore = !((pagePayload as ApiListResponse).last === true);
          page++;
        }

        const formattedItems = allData
          .filter((item) => {
            if (equipmentType === "weaponCategory") {
              const ec = (item as any).equipment_category;
              return ec?.index === "weapon" || ec?.name === "Weapon";
            }
            if (equipmentType === "armorCategory") {
              const ec = (item as any).equipment_category;
              return ec?.index === "armor" || ec?.name === "Armor" || (item as any).armor_category != null;
            }
            return true;
          })
          .map((item) => {
            const pathId = getPathId(item);
            return formatItem(item, pathId);
          });

        setItems(formattedItems);
        setCurrentPage(1);
      } catch (err) {
        console.error("Error fetching items:", err);
        setItems([]);
      } finally {
        setIsLoading(false);
      }
    };

    fetchItems();
  }, [endpoint, basePath, categoriesEndpoint, equipmentType]);

  useEffect(() => {
    const handleResize = () => {
      setColumns(computeColumnsFromContainer());
    };

    const resizeObserver = new ResizeObserver(handleResize);
    const container = document.querySelector('.main-layout');
    if (container) {
      resizeObserver.observe(container);
    }

    return () => {
      resizeObserver.disconnect();
    };
  }, []);

  const filteredAndSearchedItems = useMemo(() => {
    let result = items;

    if (selectedCategory) {
      const allowedIndexes = categoryItemIndexes.get(selectedCategory);
      if (allowedIndexes && allowedIndexes.size > 0) {
        result = result.filter((item) => allowedIndexes.has(item.itemIndex || ""));
      } else {
        result = filterEquipmentByCategory(result, selectedCategory, equipmentType);
      }
    }

    if (searchQuery.trim()) {
      const normalizedQuery = normalizeText(searchQuery);
      result = result.filter((item) => {
        const queryMatch =
          !normalizedQuery ||
          normalizeText(item.title).includes(normalizedQuery) ||
          item.searchText.includes(normalizedQuery);
        return queryMatch;
      });
    }

    return result;
  }, [items, selectedCategory, searchQuery, equipmentType]);

  const paginatedItems = useMemo(() => {
    const startIdx = (currentPage - 1) * itemsPerPage;
    const endIdx = startIdx + itemsPerPage;
    return filteredAndSearchedItems.slice(startIdx, endIdx);
  }, [filteredAndSearchedItems, currentPage, itemsPerPage]);

  const newTotalPages = useMemo(() => {
    return Math.max(1, Math.ceil(filteredAndSearchedItems.length / itemsPerPage));
  }, [filteredAndSearchedItems.length, itemsPerPage]);

  useEffect(() => {
    setTotalPages(newTotalPages);
    setCurrentPage(1);
  }, [newTotalPages]);

  const handleCategoryChange = (categoryIndex: string | null) => {
    setSelectedCategory(categoryIndex);
    setCurrentPage(1);
  };

  const isMobile = columns <= 2;
  const cardWidth = isMobile ? 172 : 230; 
  const gap = isMobile ? 16 : 32;

  const gridStyle: React.CSSProperties = {
    display: 'grid',
    gridTemplateColumns: `repeat(${columns}, minmax(0, 1fr))`,
    columnGap: `${gap}px`,
    rowGap: `${gap}px`,
    justifyContent: 'center',
    width: '100%',
    maxWidth: `${columns * cardWidth + (columns - 1) * gap}px`,
    margin: '0 auto',
    paddingBottom: '20px',
  };

  return (
    <div className="w-full flex flex-col flex-1">
      {backgroundVariant ? (
        <PageBackground variant={backgroundVariant} />
      ) : (
        <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />
      )}

      <PageTitle title={title} />

      {!categoriesLoading && categories.length > 0 && (
        <div className="equipment-filters">
          <div className="filter-buttons">
            <button
              className={`filter-btn ${selectedCategory === null ? "active" : ""}`}
              onClick={() => handleCategoryChange(null)}
            >
              All {title}
            </button>
            {categories.map((category) => (
              <button
                key={category.originalIndex}
                className={`filter-btn ${selectedCategory === category.originalIndex ? "active" : ""}`}
                onClick={() => handleCategoryChange(category.originalIndex)}
              >
                {category.name}
              </button>
            ))}
          </div>
        </div>
      )}

      {isLoading ? (
        <div style={gridStyle}></div>
      ) : (
        <>
          <div style={gridStyle}>
            {paginatedItems.map((item) => (
              <CategoryCard
                key={item.id}
                id={item.id}
                title={item.title}
                path={item.path}
                icon={item.icon}
              />
            ))}
          </div>

          {totalPages > 1 && (
            <Pagination
              currentPage={currentPage}
              totalPages={totalPages}
              onPageChange={setCurrentPage}
            />
          )}
        </>
      )}
    </div>
  );
};
