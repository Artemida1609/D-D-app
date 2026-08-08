import { useEffect, useMemo, useState } from "react";
import { CategoryListPage } from "../CategoryListPage/CategoryListPage";
import { Pagination } from "../../shared/ui/Pagination/Pagination";
import { filterCategories } from "../../shared/constants/filterCategories";
import { useSearchStore } from "../../shared/store/searchStore";
import { API_BASE_URL } from "../../shared/api/config";
import { getFavoriteUniqueId, getEntityTypeFromPath } from "../../shared/utils/favoritesUtils";

interface AsyncCategoryPageProps {
  title: string;
  endpoint: string;
  basePath: string;
  backgroundVariant?: "signup" | "login" | "account" | "favorites";
}

const ROWS_PER_PAGE = 6;

const computeColumnsFromContainer = () => {
  if (typeof window === 'undefined') return 2;
  
  const container = document.querySelector('.main-layout') as HTMLElement | null;
  const containerWidth = container ? container.clientWidth : window.innerWidth;

  
  const isMobile = containerWidth <= 361;
  const cardWidth = isMobile ? 172 : 230; 
  const gap = isMobile ? 16 : 32; 

  
  for (let cols = 6; cols >= 1; cols--) {
    const required = cols * cardWidth + (cols - 1) * gap;
    if (required <= containerWidth) return cols;
  }

  return 1;
};


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

interface ApiListItem {
  name?: string;
  title?: string;
  index?: string | number;
  id?: string | number;
  url?: string;
  path?: string;
  type?: string;
  category?: string;
  description?: string;
  fullName?: string;
  slug?: string;
  image?: string;
  imageUrl?: string;
  icon?: string;
  equipment?: ApiListItem[];
  [key: string]: unknown;
}

interface ApiListResponse {
  content?: ApiListItem[];
  results?: ApiListItem[];
  data?: ApiListItem[];
  totalPages?: number;
}

const matchesSubcategory = (filterKey: string, searchText: string) => {
  const token = tokenFromFilter(filterKey);
  const keywords = filterKeywordsMap[token] || [token];
  return keywords.some((keyword) => searchText.includes(keyword));
};

const buildItemSearchText = (item: ApiListItem) => {
  const textParts = [
    item.name,
    item.title,
    item.index,
    item.id,
    item.url,
    item.path,
    item.type,
    item.category,
    item.description,
    item.fullName,
    item.slug,
  ].filter((value): value is string => typeof value === "string" && value.trim().length > 0);

  return normalizeText([JSON.stringify(item), ...textParts].join(" "));
};

const getItemIdentifier = (item: ApiListItem) => {
  const identifier = item.index ?? item.id;
  if (typeof identifier === "string" && identifier.trim().length > 0) {
    return identifier;
  }

  if (typeof identifier === "number") {
    return String(identifier);
  }

  return undefined;
};

const toApiItems = (payload: ApiListResponse | ApiListItem[] | null | undefined): ApiListItem[] => {
  if (Array.isArray(payload)) {
    return payload;
  }

  if (!payload) {
    return [];
  }

  return payload.content ?? payload.results ?? payload.data ?? [];
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
  const [error, setError] = useState<string | null>(null);

  const [columns, setColumns] = useState<number>(() => computeColumnsFromContainer());
  const [itemsPerPage, setItemsPerPage] = useState<number>(columns * ROWS_PER_PAGE);

  const query = useSearchStore((state) => state.query);
  const chosenSubcategories = useSearchStore((state) => state.chosenSubcategories);

  useEffect(() => {
    let timeout: number | undefined;
    const handleResize = () => {
      if (timeout) window.clearTimeout(timeout);
      timeout = window.setTimeout(() => {
        const newCols = computeColumnsFromContainer();
        if (newCols !== columns) {
          setColumns(newCols);
          setItemsPerPage(newCols * ROWS_PER_PAGE);
          setCurrentPage(1);
        }
      }, 120);
    };

    window.addEventListener("resize", handleResize);

    const container = document.querySelector(".main-layout") as HTMLElement | null;
    let ro: ResizeObserver | null = null;
    if (container && typeof ResizeObserver !== "undefined") {
      ro = new ResizeObserver(() => {
        const newCols = computeColumnsFromContainer();
        if (newCols !== columns) {
          setColumns(newCols);
          setItemsPerPage(newCols * ROWS_PER_PAGE);
          setCurrentPage(1);
        }
      });
      ro.observe(container);
    }

    return () => {
      window.removeEventListener("resize", handleResize);
      if (timeout) window.clearTimeout(timeout);
      if (ro && container) ro.unobserve(container);
    };
  }, [columns]);

  const hasActiveSearch = normalizeText(query).length > 0 || chosenSubcategories.length > 0;

  useEffect(() => {
    if (!hasActiveSearch || currentPage === 1) {
      return;
    }

    const timeoutId = window.setTimeout(() => {
      setCurrentPage(1);
    }, 0);

    return () => window.clearTimeout(timeoutId);
  }, [hasActiveSearch, currentPage]);

  useEffect(() => {
    let isCancelled = false;

    const fetchData = async () => {
      try {
        setIsLoading(true);
        setError(null);

        const targetEndpoint = endpoint;
        const isEquipmentPage = targetEndpoint.includes("/equipment-categories/");
        const shouldFetchAll = hasActiveSearch;

        const fullEndpoint = targetEndpoint.startsWith("http")
          ? targetEndpoint
          : `${API_BASE_URL}${targetEndpoint.startsWith("/") ? "" : "/"}${targetEndpoint}`;

        const formatItems = (dataArray: ApiListItem[]) =>
          dataArray.map((item) => {
            let imagePath = item.image || item.imageUrl || item.icon || "";

            if (typeof imagePath === "string" && imagePath.startsWith("/api/images/")) {
              imagePath = `https://www.dnd5eapi.co${imagePath}`;
            }

            const rawPathId = item.index ?? item.id;
            let pathId = typeof rawPathId === "string" || typeof rawPathId === "number"
              ? String(rawPathId)
              : "";

            if (typeof item.url === "string" && !pathId) {
              const urlParts = item.url.split("/").filter(Boolean);
              pathId = urlParts[urlParts.length - 1] || "";
            }

            const entityType = getEntityTypeFromPath(basePath) || pathId || "";
            const uniqueId = pathId ? getFavoriteUniqueId(entityType, pathId) : Math.random().toString();

            return {
              id: uniqueId,
              title: item.name || item.title || "Unknown",
              path: `${basePath}/${pathId}`,
              icon: imagePath,
              searchText: buildItemSearchText(item),
            };
          });

        if (isEquipmentPage) {
          const response = await fetch(fullEndpoint);
          if (!response.ok) {
            throw new Error("Failed to fetch");
          }

          const data = (await response.json()) as ApiListResponse | ApiListItem[];
          const rawCategories = toApiItems(data);
          let allEquipment: ApiListItem[] = [];

          rawCategories.forEach((category) => {
            if (Array.isArray(category.equipment)) {
              allEquipment = [...allEquipment, ...category.equipment];
            }
          });

          const uniqueMap = new Map<string, ApiListItem>();
          allEquipment.forEach((item) => {
            const id = getItemIdentifier(item);
            if (id && !uniqueMap.has(id)) {
              uniqueMap.set(id, item);
            }
          });

          const formattedItems = formatItems(Array.from(uniqueMap.values()));
          if (!isCancelled) {
            setItems(formattedItems);
            setTotalPages(Math.max(1, Math.ceil(formattedItems.length / itemsPerPage)));
          }
          return;
        }

        if (shouldFetchAll) {
          const firstResponse = await fetch(
            fullEndpoint.includes("?")
              ? `${fullEndpoint}&page=0&size=200`
              : `${fullEndpoint}?page=0&size=200`
          );

          if (!firstResponse.ok) {
            throw new Error("Failed to fetch");
          }

          const firstPayload = (await firstResponse.json()) as ApiListResponse | ApiListItem[];
          const totalPagesFromBackend = Array.isArray(firstPayload) ? 1 : firstPayload.totalPages ?? 1;
          const allData: ApiListItem[] = [];

          for (let page = 0; page < totalPagesFromBackend; page += 1) {
            const pageUrl = fullEndpoint.includes("?")
              ? `${fullEndpoint}&page=${page}&size=200`
              : `${fullEndpoint}?page=${page}&size=200`;
            const pageResponse = await fetch(pageUrl);

            if (!pageResponse.ok) {
              throw new Error("Failed to fetch");
            }

            const pagePayload = (await pageResponse.json()) as ApiListResponse | ApiListItem[];
            const pageData = toApiItems(pagePayload);

            allData.push(...pageData);
          }

          const formattedItems = formatItems(allData);
          if (!isCancelled) {
            setItems(formattedItems);
            setTotalPages(Math.max(1, Math.ceil(formattedItems.length / itemsPerPage)));
          }
          return;
        }

        const backendPage = currentPage - 1;
        const fetchUrl = fullEndpoint.includes("?")
          ? `${fullEndpoint}&page=${backendPage}&size=${itemsPerPage}`
          : `${fullEndpoint}?page=${backendPage}&size=${itemsPerPage}`;

        const response = await fetch(fetchUrl);
        if (!response.ok) {
          throw new Error("Failed to fetch");
        }

        const data = (await response.json()) as ApiListResponse | ApiListItem[];
        const dataArray = toApiItems(data);
        const formattedItems = formatItems(dataArray);

        if (!isCancelled) {
          setItems(formattedItems);
          setTotalPages(Array.isArray(data) ? Math.max(1, Math.ceil(dataArray.length / itemsPerPage)) : data.totalPages ?? Math.max(1, Math.ceil(dataArray.length / itemsPerPage)));
        }
      } catch (fetchError: unknown) {
        console.error("Error fetching data:", fetchError);
        if (!isCancelled) {
          const message = fetchError instanceof Error ? fetchError.message : "Failed to load items.";
          setError(message);
        }
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
  }, [endpoint, basePath, title, currentPage, itemsPerPage, hasActiveSearch]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const visibleItems = useMemo(() => {
    const normalizedQuery = normalizeText(query);

    const filteredItems = items.filter((item) => {
      const queryMatch =
        !normalizedQuery ||
        normalizeText(item.title).includes(normalizedQuery) ||
        item.searchText.includes(normalizedQuery);

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

    if (!hasActiveSearch) {
      return filteredItems;
    }

    const startIndex = (currentPage - 1) * itemsPerPage;
    return filteredItems.slice(startIndex, startIndex + itemsPerPage);
  }, [items, query, chosenSubcategories, currentPage, itemsPerPage, hasActiveSearch]);

  const totalFilteredPages = useMemo(() => {
    if (!hasActiveSearch) {
      return totalPages;
    }

    const normalizedQuery = normalizeText(query);
    const filteredItems = items.filter((item) => {
      const queryMatch =
        !normalizedQuery ||
        normalizeText(item.title).includes(normalizedQuery) ||
        item.searchText.includes(normalizedQuery);

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

    return Math.max(1, Math.ceil(filteredItems.length / itemsPerPage));
  }, [chosenSubcategories, hasActiveSearch, items, itemsPerPage, query, totalPages]);

  if (isLoading) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        Loading...
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        <div className="text-center">
          <p className="text-lg font-medium">{error}</p>
          <p className="opacity-80 mt-2">Please try again later or choose a different category.</p>
        </div>
      </div>
    );
  }

  return (
    <div className="flex flex-col min-h-screen">
      <CategoryListPage
        title={title}
        items={visibleItems}
        backgroundVariant={backgroundVariant}
        columns={columns}
      />

      {!isLoading && visibleItems.length === 0 && (
        <div className="text-[#FFFBE4] opacity-80 text-center pb-10">
          Nothing found. Try another search query or filters.
        </div>
      )}

      {(!hasActiveSearch && totalPages > 1) || (hasActiveSearch && totalFilteredPages > 1) ? (
        <Pagination
          currentPage={currentPage}
          totalPages={hasActiveSearch ? totalFilteredPages : totalPages}
          onPageChange={handlePageChange}
        />
      ) : null}
    </div>
  );
};

