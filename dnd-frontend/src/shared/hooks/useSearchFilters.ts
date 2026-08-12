import { useEffect, useRef, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { filterCategories } from "../constants/filterCategories";
import { useSearchStore } from "../store/searchStore";
import { useSidebarStore } from "../store/sidebarStore";

export const useSearchFilters = (isSidebar: boolean) => {
  const [isOpen, setIsOpen] = useState(isSidebar);
  const [showAllChosen, setShowAllChosen] = useState(false);
  const searchRef = useRef<HTMLDivElement>(null);
  const [searchParams, setSearchParams] = useSearchParams();
  const hasHydratedFromUrlRef = useRef(false);
  const navigate = useNavigate();
  const closeAside = useSidebarStore((state) => state.closeAside);

  const query = useSearchStore((state) => state.query);
  const setQuery = useSearchStore((state) => state.setQuery);
  const activeCategory = useSearchStore((state) => state.activeCategory);
  const setActiveCategory = useSearchStore((state) => state.setActiveCategory);
  const chosenSubcategories = useSearchStore((state) => state.chosenSubcategories);
  const removeSubcategory = useSearchStore((state) => state.removeSubcategory);
  const toggleSubcategory = useSearchStore((state) => state.toggleSubcategory);

  useEffect(() => {
    if (isSidebar) return;
    const handleClickOutside = (e: MouseEvent) => {
      if (searchRef.current && !searchRef.current.contains(e.target as Node)) {
        setIsOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, [isSidebar]);

  useEffect(() => {
    if (!filterCategories.some((category) => category.categoryKey === activeCategory)) {
      setActiveCategory(filterCategories[0].categoryKey);
    }
  }, [activeCategory, setActiveCategory]);

  useEffect(() => {
    if (!hasHydratedFromUrlRef.current) {
      const urlQuery = searchParams.get("q") ?? "";
      const urlFilters = searchParams.get("filters") ?? "";
      const urlCategory = searchParams.get("category") ?? "";
      const restoredSubcategories = urlFilters
        .split(",")
        .map((value) => value.trim())
        .filter(Boolean);

      if (urlQuery || restoredSubcategories.length > 0 || urlCategory) {
        useSearchStore.setState({
          query: urlQuery,
          chosenSubcategories: restoredSubcategories,
          activeCategory: urlCategory || activeCategory,
        });
      }

      hasHydratedFromUrlRef.current = true;
      return;
    }

    const nextParams = new URLSearchParams(searchParams);

    if (query) {
      nextParams.set("q", query);
    } else {
      nextParams.delete("q");
    }

    if (chosenSubcategories.length > 0) {
      nextParams.set("filters", chosenSubcategories.join(","));
    } else {
      nextParams.delete("filters");
    }

    if (activeCategory && activeCategory !== filterCategories[0]?.categoryKey) {
      nextParams.set("category", activeCategory);
    } else {
      nextParams.delete("category");
    }

    const nextSearch = nextParams.toString();
    const currentSearch = searchParams.toString();

    if (nextSearch !== currentSearch) {
      setSearchParams(nextParams, { replace: true });
    }
  }, [activeCategory, chosenSubcategories, query, searchParams, setActiveCategory, setSearchParams]);

  const handleRemove = (sub: string) => removeSubcategory(sub);

  const handleToggle = (sub: string) => toggleSubcategory(sub);

  const handleSearch = () => {
    const params = new URLSearchParams();

    if (query.trim()) {
      params.set("q", query.trim());
    }

    if (chosenSubcategories.length > 0) {
      params.set("filters", chosenSubcategories.join(","));
    }

    if (activeCategory && activeCategory !== filterCategories[0]?.categoryKey) {
      params.set("category", activeCategory);
    }

    navigate(`/search?${params.toString()}`);

    if (!isSidebar) {
      setIsOpen(false);
    } else {
      closeAside();
    }
  };

  const handleQueryChange = (value: string) => {
    setQuery(value);
    if (!isSidebar) {
      setIsOpen(true);
    }
  };

  const visibleChosen = showAllChosen
    ? chosenSubcategories
    : chosenSubcategories.slice(0, 2);

  return {
    isOpen, setIsOpen,
    activeCategory, setActiveCategory,
    chosenSubcategories,
    query,
    setQuery: handleQueryChange,
    showAllChosen, setShowAllChosen,
    visibleChosen,
    searchRef,
    handleRemove,
    handleToggle,
    handleSearch,
  };
};