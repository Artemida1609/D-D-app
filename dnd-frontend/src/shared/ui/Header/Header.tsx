import { Link } from "react-router-dom";
import "./Header.scss";
import { ArrowDown } from "../ArrowDown";
import { SearchIcon } from "../SearchIcon";
import { useEffect, useRef, useState } from "react";
import { DnDIcon } from "../DnDIcon";
import { BurgerMenuIcon } from "../BurgerMenuIcon";
import { CloseIcon } from "../CloseIcon";
import { Button } from "../Button";
import { navItems, filterCategories } from "./index";

export const Header = ({ setActiveAside }: { setActiveAside: (active: boolean) => void }) => {
  const [isSearchDropdownOpen, setIsSearchDropdownOpen] = useState(false);
  const [language, setLanguage] = useState("EN");
  const [isMobile, setIsMobile] = useState(window.innerWidth < 992);
  const [activeCategory, setActiveCategory] = useState(
    filterCategories[0].category,
  );
  const [chosenSubcategories, setChosenSubcategories] = useState<string[]>([]);
  const [showAllChosen, setShowAllChosen] = useState(false);
  const searchRef = useRef<HTMLDivElement>(null);

  const handleRemoveChosenSubcategories = (subcategory: string) => {
    setChosenSubcategories((prev) => {
      if (prev.includes(subcategory)) {
        return prev.filter((v) => v !== subcategory);
      } else {
        return [...prev, subcategory];
      }
    });
  };

  const handleAddChosenSubcategory = (subcategory: string) => {
    setChosenSubcategories((prev) => {
      if (prev.includes(subcategory)) {
        return prev;
      }
      return [...prev, subcategory];
    });
  };

  useEffect(() => {
    const handleResize = () => setIsMobile(window.innerWidth < 992);
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  useEffect(() => {
    const handleClickOutside = (e: MouseEvent) => {
      if (searchRef.current && !searchRef.current.contains(e.target as Node)) {
        setIsSearchDropdownOpen(false);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  return (
    <header className="flex justify-between items-center w-full z-50 relative header mx-auto px-6 xl:px-0">
      {!isMobile ? (
        <>
          {/* Navigation */}
          <nav>
            <ul className="flex gap-[48px] justify-start items-center">
              {navItems.map((item) => (
                <li className="nav-item" key={item.path}>
                  <Link
                    to={item.path}
                    className="cursor-pointer nav-item__link"
                  >
                    {item.title}
                  </Link>
                  {item.subItems && (
                    <ul className="nav-item__dropdown">
                      {item.subItems?.map((subItem) => (
                        <li
                          className="nav-item__dropdown-link"
                          key={subItem.path}
                        >
                          <Link to={subItem.path}>{subItem.title}</Link>
                        </li>
                      ))}
                    </ul>
                  )}
                </li>
              ))}
            </ul>
          </nav>

          {/* Search */}
          <div
            ref={searchRef}
            className="relative flex items-center justify-center w-[280px] h-[54px]"
          >
            <div
              className={`absolute top-0 left-1/2 -translate-x-1/2 h-[54px] flex items-center justify-center transition-all duration-300 z-20 search__input-wrapper ${
                isSearchDropdownOpen
                  ? "w-[398px] bg-[#00192D] search__input-wrapper--open"
                  : "w-[280px]"
              }`}
            >
              <div
                className={`relative flex items-center transition-all duration-300 ${
                  isSearchDropdownOpen ? "w-[350px]" : "w-[280px]"
                }`}
              >
                <button className="absolute left-[12px] top-1/2 -translate-y-1/2 z-10">
                  <SearchIcon />
                </button>
                <input
                  type="text"
                  placeholder="Search"
                  className="bg-white/20 text-white text-[16px] placeholder:text-gray-400 rounded-[50px] h-[54px] w-full pl-12 focus:outline-none"
                  onFocus={() => setIsSearchDropdownOpen(true)}
                />
              </div>
            </div>

            {/* Завжди в DOM, але прихований через opacity/transform */}
            <div
              className={`absolute left-1/2 -translate-x-1/2 w-[398px] bg-[#00192D] border border-[#FFFBE4] border-t-0 rounded-b-[20px] p-6 z-10 search__dropdown transition-all duration-300 ${
                isSearchDropdownOpen
                  ? "top-[54px] opacity-100 pointer-events-auto search__dropdown--open"
                  : "top-[40px] opacity-0 pointer-events-none"
              }`}
            >
              <h2 className="text-[#FFFBE4] mb-6 search__title">Filters</h2>

              {/* Chosen subcategories */}
              <div
                className={`search__chosen mb-6 ${
                  showAllChosen ? "search__chosen--expanded" : ""
                }`}
              >
                {(showAllChosen
                  ? chosenSubcategories
                  : chosenSubcategories.slice(0, 2)
                ).map((subcategory) => (
                  <span key={subcategory} className="search__chosen-item">
                    <p>{subcategory}</p>
                    <span
                      className="cursor-pointer"
                      onClick={() =>
                        handleRemoveChosenSubcategories(subcategory)
                      }
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

              {/* Filter categories */}
              <div className="flex mb-6">
                <ul className="flex flex-col gap-2 text-[#FFFBE4] search__list search__list--categories">
                  {filterCategories.map((category) => (
                    <li
                      key={category.category}
                      className={`search__list-item cursor-pointer 
                        ${activeCategory === category.category ? "search__list-item--active" : ""}`}
                      onClick={() => setActiveCategory(category.category)}
                    >
                      <span className="font-semibold">{category.category}</span>
                    </li>
                  ))}
                </ul>
                <div className="w-[1px] min-w-[1px] self-stretch mx-6 bg-[#FFFBE4]/20"></div>
                <ul className="flex flex-col gap-2 text-[#FFFBE4] search__list search__list--subcategories">
                  {filterCategories.map((category) => (
                    <div key={category.category}>
                      {category.subcategories.map((subcategory) => (
                        <li
                          key={subcategory}
                          className={`search__list-item cursor-pointer
                          ${activeCategory === category.category ? "" : "hidden"}`}
                          onClick={() =>
                            handleAddChosenSubcategory(subcategory)
                          }
                        >
                          <span
                            className={`${chosenSubcategories.includes(subcategory) ? "search__list-item--chosen" : ""}`}
                          >
                            {subcategory}
                          </span>
                        </li>
                      ))}
                    </div>
                  ))}
                </ul>
              </div>
              <Button className="search__dropdown-btn">
                <span>Search</span>
              </Button>
            </div>
          </div>

          {/* Navigation Actions */}
          <div className="flex gap-[24px] items-center nav-actions">
            <button className="w-[100px] h-[54px] login cursor-pointer">
              Log in
            </button>
            <Link
              to="/signup"
              className="flex items-center justify-center w-[100px] h-[54px] border border-[#FFFBE4] rounded-[25px] signup cursor-pointer"
            >
              Sign up
            </Link>
            <div className="language relative">
              <button className="w-[100px] flex items-center justify-center gap-1 cursor-pointer">
                {language}
                <ArrowDown />
              </button>
              <ul className="language__dropdown">
                <li className="language__option">
                  <button onClick={() => setLanguage("EN")}>EN</button>
                </li>
                <li className="language__option">
                  <button onClick={() => setLanguage("UKR")}>UKR</button>
                </li>
              </ul>
            </div>
          </div>
        </>
      ) : (
        <>
          {/* Mobile Header */}
          <div className="flex items-center justify-between w-full h-10">
            <Link to="/">
              <DnDIcon />
            </Link>
            <span className="cursor-pointer" onClick={() => setActiveAside(true)}>
              <BurgerMenuIcon />
            </span>
          </div>
        </>
      )}
    </header>
  );
};
