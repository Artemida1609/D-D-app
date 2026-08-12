import { useEffect, useMemo, useState } from "react";
import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import { CategoryListPage } from "../CategoryListPage/CategoryListPage";
import { Pagination } from "../../shared/ui/Pagination/Pagination";
import { filterCategories } from "../../shared/constants/filterCategories";
import { useSearchStore } from "../../shared/store/searchStore";
import { ROWS_PER_PAGE, SIZE_BREAKPOINTS, GRID_CALC } from "../AsyncCategoryPage/constants/gridConfig";
import { normalizeText, matchesSubcategory } from "../AsyncCategoryPage/utils/searchUtils";
import { fetchAllSearchItems } from "./utils/fetchSearchItems";
import type { AsyncListItem } from "../AsyncCategoryPage/types/api";

const computeColumnsFromContainer = () => {
  if (typeof window === "undefined") return 2;

  const container = document.querySelector(".main-layout") as HTMLElement | null;
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

const subcategoryToCategory = new Map(
  filterCategories.flatMap((category) =>
    category.subcategories.map((subcategory) => [subcategory, category.categoryKey]),
  ),
);

export const SearchResultsPage = () => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();

  // Hydrate the store from URL params on mount so that search results
  // are shown correctly after a page reload or direct URL navigation.
  useEffect(() => {
    const urlQuery = searchParams.get("q") ?? "";
    const urlFilters = searchParams.get("filters") ?? "";
    const urlCategory = searchParams.get("category") ?? "";
    const restoredSubcategories = urlFilters
      .split(",")
      .map((v) => v.trim())
      .filter(Boolean);

    const currentState = useSearchStore.getState();
    const storeIsEmpty = !currentState.query && currentState.chosenSubcategories.length === 0;

    if (storeIsEmpty && (urlQuery || restoredSubcategories.length > 0 || urlCategory)) {
      useSearchStore.setState({
        query: urlQuery,
        chosenSubcategories: restoredSubcategories,
        ...(urlCategory ? { activeCategory: urlCategory } : {}),
      });
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const query = useSearchStore((state) => state.query);
  const chosenSubcategories = useSearchStore((state) => state.chosenSubcategories);

  const [items, setItems] = useState<AsyncListItem[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [columns, setColumns] = useState<number>(() => computeColumnsFromContainer());

  const itemsPerPage = columns * ROWS_PER_PAGE;
  // Also check URL params as fallback so there's no "Enter query" flash
  // before the store hydration effect sets the values from the URL.
  const urlQuery = searchParams.get("q") ?? "";
  const urlFilters = searchParams.get("filters") ?? "";
  const hasActiveSearch =
    normalizeText(query).length > 0 ||
    chosenSubcategories.length > 0 ||
    normalizeText(urlQuery).length > 0 ||
    urlFilters.trim().length > 0;

  useEffect(() => {
    let timeout: number | undefined;

    const handleResize = () => {
      if (timeout) window.clearTimeout(timeout);
      timeout = window.setTimeout(() => {
        setColumns(computeColumnsFromContainer());
        setCurrentPage(1);
      }, GRID_CALC.resizeDebounceMs);
    };

    window.addEventListener("resize", handleResize);

    const container = document.querySelector(".main-layout") as HTMLElement | null;
    let ro: ResizeObserver | null = null;

    if (container && typeof ResizeObserver !== "undefined") {
      ro = new ResizeObserver(() => {
        setColumns(computeColumnsFromContainer());
        setCurrentPage(1);
      });
      ro.observe(container);
    }

    return () => {
      window.removeEventListener("resize", handleResize);
      if (timeout) window.clearTimeout(timeout);
      if (ro && container) ro.unobserve(container);
    };
  }, []);

  useEffect(() => {
    let isCancelled = false;

    const loadItems = async () => {
      try {
        setIsLoading(true);
        setError(null);
        const allItems = await fetchAllSearchItems();

        if (!isCancelled) {
          setItems(allItems);
        }
      } catch (fetchError: unknown) {
        console.error("Error fetching search data:", fetchError);
        if (!isCancelled) {
          const message = fetchError instanceof Error ? fetchError.message : "Failed to load search data.";
          setError(message);
        }
      } finally {
        if (!isCancelled) {
          setIsLoading(false);
        }
      }
    };

    loadItems();

    return () => {
      isCancelled = true;
    };
  }, []);

  useEffect(() => {
    setCurrentPage(1);
  }, [query, chosenSubcategories]);

  const filteredItems = useMemo(() => {
    // Use store values; fall back to URL params before the hydration effect runs.
    const effectiveQuery = query || urlQuery;
    const effectiveSubcategories =
      chosenSubcategories.length > 0
        ? chosenSubcategories
        : urlFilters
            .split(",")
            .map((v) => v.trim())
            .filter(Boolean);

    const normalizedQuery = normalizeText(effectiveQuery);

    return items.filter((item) => {
      const queryMatch =
        !normalizedQuery ||
        normalizeText(item.title).includes(normalizedQuery) ||
        item.searchText.includes(normalizedQuery);

      if (!queryMatch) {
        return false;
      }

      if (effectiveSubcategories.length === 0) {
        return true;
      }

      const groupedFilters = new Map<string, string[]>();

      effectiveSubcategories.forEach((filterKey) => {
        const category = subcategoryToCategory.get(filterKey) || "filters.misc";
        const current = groupedFilters.get(category) || [];
        groupedFilters.set(category, [...current, filterKey]);
      });

      return Array.from(groupedFilters.values()).every((filtersInCategory) =>
        filtersInCategory.some((filterKey) => matchesSubcategory(filterKey, item.searchText)),
      );
    });
  }, [items, query, chosenSubcategories, urlQuery, urlFilters]);

  const visibleItems = useMemo(() => {
    const startIndex = (currentPage - 1) * itemsPerPage;
    return filteredItems.slice(startIndex, startIndex + itemsPerPage);
  }, [filteredItems, currentPage, itemsPerPage]);

  const totalFilteredPages = Math.max(1, Math.ceil(filteredItems.length / itemsPerPage));

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  if (isLoading) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        {t("ui.loading")}
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        <div className="text-center">
          <p className="text-lg font-medium">{error}</p>
          <p className="opacity-80 mt-2">{t("search.tryAgain")}</p>
        </div>
      </div>
    );
  }

  if (!hasActiveSearch) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        <p className="opacity-80">{t("search.enterQuery")}</p>
      </div>
    );
  }

  const effectiveTitleQuery = (query || urlQuery).trim();
  const pageTitle = effectiveTitleQuery
    ? `${t("search.results")}: "${effectiveTitleQuery}"`
    : t("search.results");

  return (
    <div className="flex flex-col min-h-screen">
      <CategoryListPage title={pageTitle} items={visibleItems} columns={columns} />

      {visibleItems.length === 0 && (
        <div className="text-[#FFFBE4] opacity-80 text-center pb-10">
          {t("search.nothingFound")}
        </div>
      )}

      {totalFilteredPages > 1 && (
        <Pagination
          currentPage={currentPage}
          totalPages={totalFilteredPages}
          onPageChange={handlePageChange}
        />
      )}
    </div>
  );
};
