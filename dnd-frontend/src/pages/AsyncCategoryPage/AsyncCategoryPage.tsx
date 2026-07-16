import { useEffect, useMemo, useState } from "react";
import { CategoryListPage } from "../CategoryListPage/CategoryListPage";
import { Pagination } from "../../shared/ui/Pagination/Pagination";
import { filterCategories } from "../../shared/constants/filterCategories";
import { useSearchStore } from "../../shared/store/searchStore";

interface AsyncCategoryPageProps {
  title: string;
  endpoint: string;
  basePath: string;
  backgroundVariant?: "signup" | "login" | "account" | "favorites";
}

const API_BASE_URL = import.meta.env.VITE_API_URL || "http://16.171.136.146";
const ITEMS_PER_PAGE = 18;

const subcategoryToCategory = new Map(
  filterCategories.flatMap((category) =>
    category.subcategories.map((subcategory) => [subcategory, category.categoryKey]),
  ),
);

const filterKeywordsMap: Record<string, string[]> = {
  humanoid: ["humanoid"],
  beast: ["beast"],
  undead: ["undead"],
  dragon: ["dragon"],
  giant: ["giant"],
  warrior: ["warrior", "fighter", "barbarian", "paladin", "monk"],
  mage: ["mage", "wizard", "sorcerer", "warlock"],
  rogue: ["rogue", "thief", "assassin", "bard"],
  cleric: ["cleric", "priest", "druid"],
  ranger: ["ranger", "hunter"],
  melee: ["melee", "sword", "axe", "mace", "dagger", "spear", "reach"],
  ranged: ["ranged", "bow", "crossbow", "thrown", "ammunition"],
  magic: ["magic", "spell", "arcane", "wand", "staff", "focus", "enchanted"],
  siege: ["siege", "ballista", "cannon", "catapult"],
  light: ["light"],
  medium: ["medium"],
  heavy: ["heavy"],
  shields: ["shield"],
  offensive: ["offensive", "damage", "attack"],
  defensive: ["defensive", "ward", "protection"],
  utility: ["utility", "utility spell", "support"],
  healing: ["healing", "heal"],
};

const normalizeText = (value: string) => value.toLowerCase().trim();

const tokenFromFilter = (filterKey: string) => filterKey.split(".").pop() || filterKey;

const matchesSubcategory = (filterKey: string, searchText: string) => {
  const token = tokenFromFilter(filterKey);
  const keywords = filterKeywordsMap[token] || [token];
  return keywords.some((keyword) => searchText.includes(keyword));
};

interface AsyncListItem {
  id: string;
  title: string;
  path: string;
  icon: string;
  searchText: string;
}

export const AsyncCategoryPage = ({ title, endpoint, basePath, backgroundVariant }: AsyncCategoryPageProps) => {
  const [items, setItems] = useState<AsyncListItem[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  const query = useSearchStore((state) => state.query);
  const chosenSubcategories = useSearchStore((state) => state.chosenSubcategories);

  const hasActiveSearch = normalizeText(query).length > 0 || chosenSubcategories.length > 0;

  useEffect(() => {
    if (hasActiveSearch && currentPage !== 1) {
      setCurrentPage(1);
    }
  }, [hasActiveSearch, currentPage]);

  useEffect(() => {
    let isCancelled = false;

    const fetchData = async () => {
      try {
        setIsLoading(true);

        const titleLower = title.toLowerCase();
        let targetEndpoint = endpoint;

        if (titleLower === "armors" || titleLower === "armor") {
          targetEndpoint = "/api/equipment-categories/armor";
        } else if (titleLower === "weapons" || titleLower === "weapon") {
          targetEndpoint = "/api/equipment-categories/weapon";
        } else if (titleLower === "gear") {
          targetEndpoint = "/api/equipment-categories/gear";
        }

        const isEquipmentPage = ["armors", "armor", "weapons", "weapon", "gear"].includes(titleLower);

        const fullEndpoint = targetEndpoint.startsWith("http")
          ? targetEndpoint
          : `${API_BASE_URL}${targetEndpoint.startsWith('/') ? '' : '/'}${targetEndpoint}`;

        let fetchUrl = fullEndpoint;
        if (!isEquipmentPage) {
          const backendPage = currentPage - 1;
          fetchUrl = fullEndpoint.includes("?")
            ? `${fullEndpoint}&page=${backendPage}&size=${ITEMS_PER_PAGE}`
            : `${fullEndpoint}?page=${backendPage}&size=${ITEMS_PER_PAGE}`;
        }

        const response = await fetch(fetchUrl);
        
        if (!response.ok) {
          throw new Error(`Failed to fetch`);
        }

        const data = await response.json();
        let dataArray: any[] = [];

        if (isEquipmentPage) {
          const rawCategories = data.content || data.results || data.data || (Array.isArray(data) ? data : []);
          let allEquipment: any[] = [];

          rawCategories.forEach((category: any) => {
            if (category.equipment && Array.isArray(category.equipment)) {
              allEquipment = [...allEquipment, ...category.equipment];
            }
          });

          const uniqueMap = new Map();
          allEquipment.forEach((item: any) => {
            const id = item.index || item.id;
            if (id && !uniqueMap.has(id)) {
              uniqueMap.set(id, item);
            }
          });
          
          dataArray = Array.from(uniqueMap.values());
        } else {
          if (Array.isArray(data)) {
            dataArray = data;
          } else if (data && Array.isArray(data.content)) {
            dataArray = data.content;
          } else if (data && Array.isArray(data.results)) {
            dataArray = data.results;
          } else if (data && data.data && Array.isArray(data.data)) {
            dataArray = data.data;
          }
        }

        let paginatedData = dataArray;

        if (!isEquipmentPage && data.totalPages !== undefined) {
          setTotalPages(data.totalPages);
        } else {
          setTotalPages(Math.ceil(dataArray.length / ITEMS_PER_PAGE) || 1);
          const startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
          paginatedData = dataArray.slice(startIndex, startIndex + ITEMS_PER_PAGE);
        }

        const formattedItems = paginatedData.map((item: any) => {
          let imagePath = item.image || item.imageUrl || item.icon || "";

          if (imagePath.startsWith("/api/images/")) {
            imagePath = `https://www.dnd5eapi.co${imagePath}`;
          }

          let pathId = item.index || item.id;
          if (item.url && !pathId) {
            const urlParts = item.url.split('/').filter(Boolean);
            pathId = urlParts[urlParts.length - 1];
          }

          return {
            id: pathId || Math.random().toString(),
            title: item.name || item.title || "Unknown",
            path: `${basePath}/${pathId}`,
            icon: imagePath,
            searchText: normalizeText(JSON.stringify(item || {})),
          };
        });

        if (!isCancelled) {
          setItems(formattedItems);
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      } finally {
        if (!isCancelled) {
          setIsLoading(false);
        }
      }
    };

    fetchData();

    return () => {
      isCancelled = true;
    };
  }, [endpoint, basePath, title, currentPage]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const filteredItems = useMemo(() => {
    const normalizedQuery = normalizeText(query);

    return items.filter((item) => {
      const queryMatch =
        !normalizedQuery
        || normalizeText(item.title).includes(normalizedQuery)
        || item.searchText.includes(normalizedQuery);

      if (!queryMatch) {
        return false;
      }

      if (chosenSubcategories.length === 0) {
        return true;
      }

      const groupedFilters = new Map<string, string[]>();

      chosenSubcategories.forEach((filterKey) => {
        const category = subcategoryToCategory.get(filterKey) || "filters.misc";
        const current = groupedFilters.get(category) || [];
        groupedFilters.set(category, [...current, filterKey]);
      });

      return Array.from(groupedFilters.values()).every((filtersInCategory) =>
        filtersInCategory.some((filterKey) => matchesSubcategory(filterKey, item.searchText)),
      );
    });
  }, [items, query, chosenSubcategories]);

  if (isLoading) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        Loading...
      </div>
    );
  }

  return (
    <div className="flex flex-col min-h-screen">
      <CategoryListPage
        title={title}
        items={filteredItems}
        backgroundVariant={backgroundVariant}
      />

      {!isLoading && filteredItems.length === 0 && (
        <div className="text-[#FFFBE4] opacity-80 text-center pb-10">
          Nothing found. Try another search query or filters.
        </div>
      )}

      {!hasActiveSearch && totalPages > 1 && (
        <Pagination
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={handlePageChange}
        />
      )}
    </div>
  );
};
