import { Link } from "react-router-dom";
import { DnDIcon } from "../Icons/DnDIcon";
import "./SideBar.scss";
import { SearchIcon } from "../Icons/SearchIcon";
import { NavActions } from "../NavActions/NavActions";
import { NavCategories } from "../NavCategories/NavCategories";
import { BurgerMenuIcon } from "../Icons/BurgerMenuIcon";
import { motion } from "framer-motion";
import { useState } from "react";
import { ArrowLeft } from "../Icons/ArrowLeft";
import { Button } from "../Icons/Button";
import { CloseIcon } from "../Icons/CloseIcon";
import { useSearchFilters } from "../../hooks/useSearchFilters";
import { filterCategories } from "../../constants/filterCategories";

export const SideBar = ({
  setActiveAside,
}: {
  setActiveAside: (active: boolean) => void;
}) => {
  const [isSearchFocused, setIsSearchFocused] = useState(false);
  const { 
      activeCategory, setActiveCategory,
      chosenSubcategories,
      showAllChosen, setShowAllChosen,
      visibleChosen,
      handleRemove,
      handleToggle,
    } = useSearchFilters(true);

  return (
    <motion.aside
      layout
      className="sidebar absolute w-screen h-screen bg-[#00192D] text-[#FFFBE4] px-4 md:px-10 pb-6 pt-11 z-99 flex flex-col"
    >
      {/* header */}
      <div className="flex justify-between items-center mb-6">
        <Link to="/" onClick={() => setActiveAside(false)}>
          <DnDIcon />
        </Link>

        <span className="cursor-pointer" onClick={() => setActiveAside(false)}>
          <BurgerMenuIcon />
        </span>
      </div>

      {/* back btn */}
      <motion.div
        initial={false}
        animate={{
          opacity: isSearchFocused ? 1 : 0,
          height: isSearchFocused ? "auto" : 0,
          marginBottom: isSearchFocused ? 24 : 0,
        }}
        transition={{
          duration: 0.45,
          ease: [0.4, 0, 0.2, 1],
        }}
        style={{
          overflow: "hidden",
        }}
        className="flex items-center gap-4"
      >
        <button
          className="flex items-center gap-4 cursor-pointer"
          onClick={() => setIsSearchFocused(false)}
        >
          <ArrowLeft />
          <span className="sidebar__back-text">Search</span>
        </button>
      </motion.div>

      {/* Categories */}
      <motion.div
        layout
        animate={{
          opacity: isSearchFocused ? 0 : 1,
          height: isSearchFocused ? 0 : "auto",
          marginBottom: isSearchFocused ? 0 : 24,
        }}
        transition={{
          duration: 0.5,
          ease: [0.4, 0, 0.2, 1],
        }}
        style={{
          overflow: "hidden",
        }}
      >
        <NavCategories setActiveAside={setActiveAside} />

        <hr className="w-full h-[1px] bg-[#FFFBE4] mb-6" />
      </motion.div>

      {/* Search */}
      <motion.div
        layout
        transition={{
          layout: {
            type: "spring",
            stiffness: 70,
            damping: 18,
          },
        }}
        className="sidebar__search w-full self-center mb-6"
      >
        <div className="sidebar__search__input-wrapper">
          <div className="sidebar__search__input-inner">
            <button className="sidebar__search__input-icon">
              <SearchIcon />
            </button>

            <input
              type="text"
              placeholder="Search"
              className="sidebar__search__input"
              onFocus={() => setIsSearchFocused(true)}
            />
          </div>
        </div>
      </motion.div>

      {/* Search Filters з'являються коли пошук активний */}
      <motion.div
        initial={false}
        animate={{
          opacity: isSearchFocused ? 1 : 0,
          height: isSearchFocused ? "auto" : 0,
          marginTop: isSearchFocused ? 24 : 0,
        }}
        transition={{
          duration: 0.45,
          ease: [0.4, 0, 0.2, 1],
        }}
        style={{
          overflow: "hidden",
        }}
        className="search search--sidebar"
      >
        <div
          className={`search__dropdown search__dropdown--open`}
        >
          <h2 className="search__title mb-6">Filters</h2>

          <div
            className={`search__chosen ${showAllChosen ? "search__chosen--expanded" : ""}`}
          >
            {visibleChosen.map((sub) => (
              <span key={sub} className="search__chosen-item ">
                <p>{sub}</p>
                <span
                  className="cursor-pointer"
                  onClick={() => handleRemove(sub)}
                >
                  <CloseIcon />
                </span>
              </span>
            ))}
            {chosenSubcategories.length > 2 && (
              <span
                className="search__chosen-more cursor-pointer"
                onClick={() => setShowAllChosen((prev) => !prev)}
              >
                {showAllChosen
                  ? "less"
                  : `+${chosenSubcategories.length - 2} more`}
              </span>
            )}
          </div>

          <div className="search__lists">
            <ul className="search__list search__list--categories">
              {filterCategories.map(({ category }) => (
                <li
                  key={category}
                  className={`search__list-item cursor-pointer ${
                    activeCategory === category
                      ? "search__list-item--active"
                      : ""
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
                      activeCategory !== category
                        ? "search__list-item--hidden"
                        : ""
                    }`}
                    onClick={() => handleToggle(sub)}
                  >
                    <span
                      className={
                        chosenSubcategories.includes(sub)
                          ? "search__list-item--chosen"
                          : ""
                      }
                    >
                      {sub}
                    </span>
                  </li>
                )),
              )}
            </ul>
          </div>

          <Button className="search__dropdown-btn">
            <span>Search</span>
          </Button>
        </div>
      </motion.div>

      {/* Actions */}
      <motion.div
        layout
        animate={{
          opacity: isSearchFocused ? 0 : 1,
          height: isSearchFocused ? 0 : "auto",
          marginTop: isSearchFocused ? 0 : 24,
        }}
        transition={{
          duration: 0.5,
          ease: [0.4, 0, 0.2, 1],
        }}
        style={{
          overflow: "hidden",
        }}
      >
        <hr className="w-full h-[1px] bg-[#FFFBE4] mb-6" />

        <NavActions isAside={true} closeSidebar={() => setActiveAside(false)} />
      </motion.div>
    </motion.aside>
  );
};
