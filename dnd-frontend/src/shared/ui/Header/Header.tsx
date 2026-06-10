import { Link } from "react-router-dom";
import "./Header.scss";
import { ArrowDown } from "../Icons/ArrowDown";
import { useEffect, useState } from "react";
import { DnDIcon } from "../Icons/DnDIcon";
import { BurgerMenuIcon } from "../Icons/BurgerMenuIcon";
import { navItems } from "../../constants/navItems";
import { SearchFilters } from "../SearchFilters/SearchFilters";

export const Header = ({ setActiveAside }: { setActiveAside: (active: boolean) => void }) => {
  const [language, setLanguage] = useState("EN");
  const [isMobile, setIsMobile] = useState(window.innerWidth < 992);

  useEffect(() => {
    const handleResize = () => setIsMobile(window.innerWidth < 992);
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  return (
    <header className="flex justify-between items-center w-full z-50 px-6 py-2 relative header">
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
          <SearchFilters variant="header" />

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
          <div className="flex items-center justify-between w-full h-10 px-4">
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
