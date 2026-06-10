import { useEffect, useRef, useState } from "react";
import { filterCategories } from "../../constants/filterCategories";
import { CloseIcon } from "../Icons/CloseIcon";
import { SearchIcon } from "../Icons/SearchIcon";
import { Button } from "../Icons/Button";
import "./SearchFilters.scss";

export const SearchFilters = ({
  className,
  variant = "header",
}: {
  className?: string;
  variant?: "header" | "sidebar";
}) => {
  const isSidebar = variant === "sidebar";

  const [isOpen, setIsOpen] = useState(isSidebar);
  const [activeCategory, setActiveCategory] = useState(filterCategories[0].category);
  const [chosenSubcategories, setChosenSubcategories] = useState<string[]>([]);
  const [showAllChosen, setShowAllChosen] = useState(false);
  const searchRef = useRef<HTMLDivElement>(null);

  const handleRemove = (sub: string) =>
    setChosenSubcategories((prev) => prev.filter((v) => v !== sub));

  const handleAdd = (sub: string) =>
    setChosenSubcategories((prev) => (prev.includes(sub) ? prev : [...prev, sub]));

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

  return (
    <div
      ref={searchRef}
      className={`search ${isSidebar ? "search--sidebar" : ""} ${className ?? ""}`}
    >
      {/* Input */}
      <div className={`search__input-wrapper ${isOpen ? "search__input-wrapper--open" : ""}`}>
        <div className="search__input-inner">
          <button className="search__input-icon">
            <SearchIcon />
          </button>
          <input
            type="text"
            placeholder="Search"
            className="search__input"
            onFocus={() => !isSidebar && setIsOpen(true)}
          />
        </div>
      </div>

      {/* Dropdown / filters panel */}
      <div className={`search__dropdown ${isOpen ? "search__dropdown--open" : ""}`}>
        <h2 className="search__title">Filters</h2>

        {/* Chosen tags */}
        <div className={`search__chosen ${showAllChosen ? "search__chosen--expanded" : ""}`}>
          {(showAllChosen ? chosenSubcategories : chosenSubcategories.slice(0, 2)).map((sub) => (
            <span key={sub} className="search__chosen-item">
              <p>{sub}</p>
              <span className="cursor-pointer" onClick={() => handleRemove(sub)}>
                <CloseIcon />
              </span>
            </span>
          ))}
          {chosenSubcategories.length > 2 && (
            <span
              className="search__chosen-more cursor-pointer"
              onClick={() => setShowAllChosen((prev) => !prev)}
            >
              {showAllChosen ? "less" : `+${chosenSubcategories.length - 2} more`}
            </span>
          )}
        </div>

        {/* Lists */}
        <div className="search__lists">
          <ul className="search__list search__list--categories">
            {filterCategories.map((category) => (
              <li
                key={category.category}
                className={`search__list-item cursor-pointer ${
                  activeCategory === category.category ? "search__list-item--active" : ""
                }`}
                onClick={() => setActiveCategory(category.category)}
              >
                <span>{category.category}</span>
              </li>
            ))}
          </ul>
          <div className="search__divider" />
          <ul className="search__list search__list--subcategories">
            {filterCategories.map((category) =>
              category.subcategories.map((sub) => (
                <li
                  key={sub}
                  className={`search__list-item cursor-pointer ${
                    activeCategory === category.category ? "" : "search__list-item--hidden"
                  }`}
                  onClick={() => handleAdd(sub)}
                >
                  <span className={chosenSubcategories.includes(sub) ? "search__list-item--chosen" : ""}>
                    {sub}
                  </span>
                </li>
              ))
            )}
          </ul>
        </div>

        <Button className="search__dropdown-btn">
          <span>Search</span>
        </Button>
      </div>
    </div>
  );
};