import { useEffect, useRef, useState } from "react";
import { filterCategories } from "../constants/filterCategories";

export const useSearchFilters = (isSidebar: boolean) => {
  const [isOpen, setIsOpen] = useState(isSidebar);
  const [activeCategory, setActiveCategory] = useState(filterCategories[0].categoryKey);
  const [chosenSubcategories, setChosenSubcategories] = useState<string[]>([]);
  const [showAllChosen, setShowAllChosen] = useState(false);
  const searchRef = useRef<HTMLDivElement>(null);

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

  const handleRemove = (sub: string) =>
    setChosenSubcategories((prev) => prev.filter((v) => v !== sub));

  const handleAdd = (sub: string) =>
    setChosenSubcategories((prev) => (prev.includes(sub) ? prev : [...prev, sub]));

  const handleToggle = (sub: string) =>
    chosenSubcategories.includes(sub) ? handleRemove(sub) : handleAdd(sub);

  const visibleChosen = showAllChosen
    ? chosenSubcategories
    : chosenSubcategories.slice(0, 2);

  return {
    isOpen, setIsOpen,
    activeCategory, setActiveCategory,
    chosenSubcategories,
    showAllChosen, setShowAllChosen,
    visibleChosen,
    searchRef,
    handleRemove,
    handleToggle,
  };
};