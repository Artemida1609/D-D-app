import { useEffect, useRef, useState } from "react";
import { filterCategories } from "../constants/filterCategories";
import { useSearchStore } from "../store/searchStore";

export const useSearchFilters = (isSidebar: boolean) => {
  const [isOpen, setIsOpen] = useState(isSidebar);
  const [showAllChosen, setShowAllChosen] = useState(false);
  const searchRef = useRef<HTMLDivElement>(null);

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

  const handleRemove = (sub: string) => removeSubcategory(sub);

  const handleToggle = (sub: string) => toggleSubcategory(sub);

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
  };
};