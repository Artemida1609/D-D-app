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
    <header className="fixed top-0 left-0 right-0 z-10 flex justify-between px-20 pt-10 header">
      {/* Navigation */}
      <nav className="p-4">
        <ul className="flex gap-6 justify-start items-center">
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

      {/* Search */}
      <div className="relative flex items-center justify-center">
        <div
          className={`h-[54px] flex items-center relative transition-all duration-300 ${
            isSearchDropdownOpen
              ? "w-[516px] bg-[#00192D] rounded-t-[20px] px-6"
              : "w-[280px]"
          }`}
        >
          <button
            className={`absolute translate-x-[12px] top-1/2 -translate-y-1/2 z-10`}
          >
            <SearchIcon />
          </button>
          <input
            type="text"
            placeholder="Search"
            className="bg-white/20 text-white placeholder:text-gray-400 rounded-[50px] h-[40px] w-full pl-9 focus:outline-none"
            onFocus={() => setIsSearchDropdownOpen(true)}
            onBlur={() => setIsSearchDropdownOpen(false)}
          />
        </div>

        {/* Dropdown */}
        {isSearchDropdownOpen && (
          <div className="absolute top-[54px] left-0 w-full bg-[#00192D] rounded-b-[20px] p-6 z-20 search-dropdown">
            <h2 className="text-[#FFFBE4] text-lg font-bold mb-4 search__title">
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

      {/* User Actions */}
      <div className="flex gap-6 items-center nav-actions">
        <button className="h-full w-[100px] login cursor-pointer">
          Log in
        </button>
        <button className="h-full w-[100px] border border-[#FFFBE4] rounded-[25px] signup cursor-pointer">
          Sign up
        </button>
        <div className="language relative">
          <button className="h-full w-[100px] flex items-center justify-center gap-1 cursor-pointer">
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
