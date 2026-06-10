import { Link } from "react-router-dom";
import { DnDIcon } from "../DnDIcon";
import { BurgerMenuIcon } from "../BurgerMenuIcon";
// import { PageTitle } from "../PageTitle";
import { SearchIcon } from "../SearchIcon";
import { CloseIcon } from "../CloseIcon";
import { Button } from "../Button";
import { useState } from "react";
import { filterCategories, navItems } from "../Header";

export const SideBar = ({
  setActiveAside,
}: {
  setActiveAside: (active: boolean) => void;
}) => {
  const [activeCategory, setActiveCategory] = useState(
    filterCategories[0].category,
  );
  const [chosenSubcategories, setChosenSubcategories] = useState<string[]>([]);
  const [showAllChosen, setShowAllChosen] = useState(false);

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
  return (
    <aside className="sidebar absolute w-screen h-screen bg-[#00192D] text-[#FFFBE4] p-6 pt-11 z-99 flex flex-col">
      <div className="flex items-center justify-between w-full h-10 mb-8">
        <Link to="/">
          <DnDIcon />
        </Link>
        <span className="cursor-pointer" onClick={() => setActiveAside(false)}>
          <BurgerMenuIcon />
        </span>
      </div>
      <div className=""></div>
      {/* <PageTitle title="Search" /> */}
      {/* Static search + filters (dropdown always open) */}
      <div className="mt-4">
        <div className="relative flex items-center justify-center w-full h-[54px]">
          <div className={`h-[54px] flex items-center justify-center transition-all duration-300 z-20 bg-[#00192D] w-full search__input-wrapper search__input-wrapper--open`}>
            <div className={`relative flex items-center transition-all duration-300 w-full`}>
              <button className="absolute left-[12px] top-1/2 -translate-y-1/2 z-10">
                <SearchIcon />
              </button>
              <input
                type="text"
                placeholder="Search"
                className="bg-white/20 text-[#FFFBE4] text-[16px] placeholder:text-gray-400 rounded-[50px] h-[54px] w-full pl-12 focus:outline-none"
              />
            </div>
          </div>
        </div>

        <div className={`mt-4 w-full bg-[#00192D] border border-[#FFFBE4] border-t-0 rounded-b-[8px] p-4 z-10`}>
          <h2 className="text-[#FFFBE4] mb-4">Filters</h2>

          <div className={`search__chosen mb-4 ${showAllChosen ? "search__chosen--expanded" : ""}`}>
            {(showAllChosen ? chosenSubcategories : chosenSubcategories.slice(0, 2)).map((subcategory) => (
              <span key={subcategory} className="search__chosen-item">
                <p>{subcategory}</p>
                <span className="cursor-pointer" onClick={() => handleRemoveChosenSubcategories(subcategory)}>
                  <CloseIcon />
                </span>
              </span>
            ))}
            {chosenSubcategories.length > 2 && (
              <span className="search__chosen-more cursor-pointer" onClick={() => setShowAllChosen((prev) => !prev)}>
                {showAllChosen ? "less" : `+${chosenSubcategories.length - 2} more`}
              </span>
            )}
          </div>

          <div className="flex mb-4">
            <ul className="flex flex-col gap-2 text-[#FFFBE4] search__list search__list--categories">
              {filterCategories.map((category) => (
                <li
                  key={category.category}
                  className={`search__list-item cursor-pointer ${activeCategory === category.category ? "search__list-item--active" : ""}`}
                  onClick={() => setActiveCategory(category.category)}
                >
                  <span className="font-semibold">{category.category}</span>
                </li>
              ))}
            </ul>
            <div className="w-[1px] min-w-[1px] self-stretch mx-4 bg-[#FFFBE4]/20"></div>
            <ul className="flex flex-col gap-2 text-[#FFFBE4] search__list search__list--subcategories">
              {filterCategories.map((category) => (
                <div key={category.category}>
                  {category.subcategories.map((subcategory) => (
                    <li
                      key={subcategory}
                      className={`search__list-item cursor-pointer ${activeCategory === category.category ? "" : "hidden"}`}
                      onClick={() => handleAddChosenSubcategory(subcategory)}
                    >
                      <span className={`${chosenSubcategories.includes(subcategory) ? "search__list-item--chosen" : ""}`}>
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

      <div className="mt-auto">
        <div className="sidebar__bottom-nav flex justify-center items-center gap-6 py-6">
          {navItems.map((item) => (
            <Link key={item.path} to={item.path} className="sidebar__bottom-item text-sm text-[#FFFBE4] opacity-80">
              {item.title}
            </Link>
          ))}
        </div>
      </div>
    </aside>
  );
};
