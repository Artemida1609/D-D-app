import { Link } from "react-router-dom";
import "./Header.scss";
import { ArrowDown } from "../ArrowDown";
import { SearchIcon } from "../SearchIcon";
import { useState } from "react";

const navItems = [
  {
    title: "Character",
    path: "/character",
    subItems: [
      { title: "Species", path: "character/species" },
      { title: "Classes", path: "character/classes" },
      { title: "Skills", path: "character/skills" },
    ],
  },
  {
    title: "Equipment",
    path: "/equipment",
    subItems: [
      { title: "Weapons", path: "equipment/weapons" },
      { title: "Armor", path: "equipment/armor" },
      { title: "Gear", path: "equipment/gear" },
    ],
  },
  {
    title: "Magic",
    path: "/magic",
    subItems: [
      { title: "Spells", path: "magic/spells" },
      { title: "Schools", path: "magic/schools" },
    ],
  },
  {
    title: "Bestiary",
    path: "/bestiary",
  },
];

export const Header = () => {
  const [isSearchDropdownOpen, setIsSearchDropdownOpen] = useState(false);
  const [language, setLanguage] = useState("EN");

  return (
    <header className="pt-10 pb-6 flex justify-between items-center w-full z-50 relative header">
      <nav>
        <ul className="flex gap-[48px] justify-start items-center">
          {navItems.map((item) => (
            <li className="nav-item" key={item.path}>
              <Link to={item.path} className="cursor-pointer nav-item__link">
                {item.title}
              </Link>
              {item.subItems && (
                <ul className="nav-item__dropdown">
                  {item.subItems?.map((subItem) => (
                    <li className="nav-item__dropdown-link" key={subItem.path}>
                      <Link to={subItem.path}>{subItem.title}</Link>
                    </li>
                  ))}
                </ul>
              )}
            </li>
          ))}
        </ul>
      </nav>

      <div className="relative flex items-center justify-center w-[280px] h-[54px]">
        <div
          className={`absolute top-0 left-1/2 -translate-x-1/2 h-[54px] flex items-center justify-center transition-all duration-300 z-20 ${
            isSearchDropdownOpen
              ? "w-[398px] bg-[#00192D] rounded-t-[20px]"
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
              onBlur={() => setIsSearchDropdownOpen(false)}
            />
          </div>
        </div>

        {isSearchDropdownOpen && (
          <div className="absolute top-[54px] left-1/2 -translate-x-1/2 w-[398px] bg-[#00192D] rounded-b-[20px] p-6 z-10 search__dropdown">
            <h2 className="text-[#FFFBE4] text-[40px] font-bold mb-4 search__title">
              Filters
            </h2>
            <div className="search__chosen h-10"></div>
            <ul className="flex flex-col gap-2 text-[#FFFBE4] search__list">
              <li>Species</li>
              <li>Classes</li>
              <li>Weapons</li>
              <li>Armors</li>
              <li>Spells</li>
            </ul>
          </div>
        )}
      </div>

      <div className="flex gap-[24px] items-center nav-actions">
        <button className="w-[100px] h-[54px] login cursor-pointer">Log in</button>
        <Link
          to="/signup"
          className="flex items-center justify-center w-[100px] h-[54px] border border-[#FFFBE4] rounded-[25px] signup cursor-pointer"
        >
          Sign up
        </Link>
        <div className="language relative">
          <button className="w-[100px] h-[54px] flex items-center justify-center gap-1 cursor-pointer">
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
    </header>
  );
};
