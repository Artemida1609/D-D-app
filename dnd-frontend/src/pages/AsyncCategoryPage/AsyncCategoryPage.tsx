import { useEffect, useMemo, useState } from "react";
import { CategoryListPage } from "../CategoryListPage/CategoryListPage";
import { Pagination } from "../../shared/ui/Pagination/Pagination";
import { filterCategories } from "../../shared/constants/filterCategories";
import { useSearchStore } from "../../shared/store/searchStore";
import { API_BASE_URL } from "../../shared/api/config";
import { getFavoriteUniqueId, getEntityTypeFromPath } from "../../shared/utils/favoritesUtils";
import { getClassIconPath } from "../../shared/utils/classIcon";
import { ROWS_PER_PAGE, SIZE_BREAKPOINTS, GRID_CALC } from "./constants/gridConfig";
import { buildItemSearchText, matchesSubcategory, normalizeText } from "./utils/searchUtils";
import { getItemIdentifier, toApiItems } from "./utils/apiItemUtils";
import type { ApiListItem, ApiListResponse, AsyncListItem } from "./types/api";

interface AsyncCategoryPageProps {
  title: string;
  endpoint: string;
  basePath: string;
  backgroundVariant?: "signup" | "login" | "account" | "favorites";
}

const subcategoryToCategory = new Map(
  filterCategories.flatMap((category) =>
    category.subcategories.map((subcategory) => [subcategory, category.categoryKey]),
  ),
);

const computeColumnsFromContainer = () => {
  if (typeof window === "undefined") return GRID_CALC.minColumns;

  const container = document.querySelector(".main-layout") as HTMLElement | null;
  const containerWidth = container ? container.clientWidth : window.innerWidth;

  const isMobile = containerWidth <= SIZE_BREAKPOINTS.mobile;
  const cardWidth = isMobile ? SIZE_BREAKPOINTS.mobilCardWidth : SIZE_BREAKPOINTS.desktopCardWidth;
  const gap = isMobile ? SIZE_BREAKPOINTS.mobileGap : SIZE_BREAKPOINTS.desktopGap;

  for (let cols = GRID_CALC.maxColumns; cols >= GRID_CALC.minColumns; cols -= 1) {
    const required = cols * cardWidth + (cols - 1) * gap;
    if (required <= containerWidth) return cols;
  }

  return GRID_CALC.minColumns;
};

const formatItems = (items: ApiListItem[], basePath: string): AsyncListItem[] => {
  const entityType = getEntityTypeFromPath(basePath) || "item";

  return items.map((item) => {
    const itemIdentifier = getItemIdentifier(item) || String(item.index ?? item.id ?? "");
    const normalizedIcon =
      typeof item.icon === "string" && item.icon.startsWith("/api/images/")
        ? `https://www.dnd5eapi.co${item.icon}`
        : item.icon || item.imageUrl || item.image || "";

    const icon =
      normalizedIcon ||
      (entityType === "classes" && itemIdentifier ? getClassIconPath(itemIdentifier) : "");

    return {
      id: getFavoriteUniqueId(entityType, itemIdentifier || item.title || item.name || Math.random().toString()),
      title: item.name || item.title || "Unknown",
      path: itemIdentifier ? `${basePath}/${itemIdentifier}` : basePath,
      icon,
      searchText: buildItemSearchText(item),
    };
  });
};

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
          : `${API_BASE_URL}${targetEndpoint.startsWith('/') ? '' : '/'}${targetEndpoint}`;

        if (isEquipmentPage) {
          const equipmentResponse = await fetch(fullEndpoint);
          if (!equipmentResponse.ok) {
            throw new Error("Failed to fetch");
          }

          const data = (await equipmentResponse.json()) as ApiListResponse | ApiListItem[];
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

          const formattedItems = formatItems(Array.from(uniqueMap.values()), basePath);
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

          const formattedItems = formatItems(allData, basePath);
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

        const pageResponse = await fetch(fetchUrl);
        if (!pageResponse.ok) {
          throw new Error("Failed to fetch");
        }

        const data = (await pageResponse.json()) as ApiListResponse | ApiListItem[];
        const dataArray = toApiItems(data);
        const formattedItems = formatItems(dataArray, basePath);

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
  }, [endpoint, basePath, currentPage, itemsPerPage, hasActiveSearch]);

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
          <p className="opacity-80 mt-2">{t("detail.pleaseTryAgain")}</p>
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

