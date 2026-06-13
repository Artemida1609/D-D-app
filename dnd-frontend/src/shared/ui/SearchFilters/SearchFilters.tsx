import { filterCategories } from "../../constants/filterCategories";
import { CloseIcon } from "../Icons/CloseIcon";
import { SearchIcon } from "../Icons/SearchIcon";
import { Button } from "../Icons/Button";
import { useSearchFilters } from "../../hooks/useSearchFilters";
import "./SearchFilters.scss";

export const SearchFilters = ({
  className,
  variant = "header",
}: {
  className?: string;
  variant?: "header" | "sidebar";
}) => {
  const isSidebar = variant === "sidebar";

  const {
    isOpen, setIsOpen,
    activeCategory, setActiveCategory,
    chosenSubcategories,
    showAllChosen, setShowAllChosen,
    visibleChosen,
    searchRef,
    handleRemove,
    handleToggle,
  } = useSearchFilters(isSidebar);

  return (
    <div
      ref={searchRef}
      className={`search ${isSidebar ? "search--sidebar" : ""} ${className ?? ""}`}
    >
      <div className={`search__input-wrapper ${isOpen ? "search__input-wrapper--open" : ""}`}>
        <div className="search__input-inner">
          <button className="search__input-icon">
            <SearchIcon />
          </button>
          <input
            type="text"
            placeholder="Search"
            className="search__input"
            onFocus={() => setIsOpen(true)}
          />
        </div>
      </div>

      <div className={`search__dropdown ${isOpen ? "search__dropdown--open" : ""}`}>
        <h2 className="search__title">Filters</h2>

        <div className={`search__chosen ${showAllChosen ? "search__chosen--expanded" : ""}`}>
          {visibleChosen.map((sub) => (
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

        <div className="search__lists">
          <ul className="search__list search__list--categories">
            {filterCategories.map(({ category }) => (
              <li
                key={category}
                className={`search__list-item cursor-pointer ${
                  activeCategory === category ? "search__list-item--active" : ""
                }`}
                onClick={() => setActiveCategory(category)}
              >
                <span>{category}</span>
              </li>
            ))}
          </ul>
          <div className="search__divider" />
          <ul className="search__list search__list--subcategories">
            {filterCategories.map(({ category, subcategories }) =>
              subcategories.map((sub) => (
                <li
                  key={sub}
                  className={`search__list-item cursor-pointer ${
                    activeCategory !== category ? "search__list-item--hidden" : ""
                  }`}
                  onClick={() => handleToggle(sub)}
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