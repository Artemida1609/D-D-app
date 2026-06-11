import { Link } from "react-router-dom";
import "./Header.scss";
import { ArrowDown } from "../Icons/ArrowDown";
import { useEffect, useState } from "react";
import { DnDIcon } from "../Icons/DnDIcon";
import { BurgerMenuIcon } from "../Icons/BurgerMenuIcon";
import { navItems } from "../../constants/navItems";
import { SearchFilters } from "../SearchFilters/SearchFilters";

export const Header = ({
  setActiveAside,
}: {
  setActiveAside: (active: boolean) => void;
}) => {
  const [language, setLanguage] = useState("EN");
  const [isMobile, setIsMobile] = useState(() => window.innerWidth < 768);
  const [isTablet, setIsTablet] = useState(() => window.innerWidth < 1350);

  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth < 768);
      setIsTablet(window.innerWidth < 1350);
    };

    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  return (
    <header className="flex justify-between items-center w-full z-50 relative header">
      {isMobile && (
        <div className="flex items-center justify-between w-full h-10 px-4">
          <Link to="/">
            <DnDIcon />
          </Link>

          {/* <div className="hidden md:block">
            <SearchFilters variant="header" />
          </div> */}

          <span className="cursor-pointer" onClick={() => setActiveAside(true)}>
            <BurgerMenuIcon />
          </span>
        </div>
      )}

      {isTablet && !isMobile && (
        <div className="flex items-center justify-between w-full h-10 px-10">
          <Link to="/">
            <DnDIcon />
          </Link>

          <div className="">
            <SearchFilters variant="header" />
          </div>

          <span className="cursor-pointer" onClick={() => setActiveAside(true)}>
            <BurgerMenuIcon />
          </span>
        </div>
      )}

      {!isTablet && !isMobile && (
        <>
          {/* Laptop */}

          {/* Navigation */}
          <div className="flex items-center gap-[48px]">
            <Link to="/">
              <DnDIcon />
            </Link>
            <nav>
              <ul className="flex gap-[32px] justify-start items-center">
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
                            onClick={(e) => e.currentTarget.blur()}
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
          </div>

          {/* Search */}
          <SearchFilters variant="header" />

          {/* Navigation Actions */}
          <div className="flex gap-[24px] items-center nav-actions">
            <Link
              to="/login"
              className="flex items-center justify-center w-[100px] h-[54px] login cursor-pointer"
            >
              Log in
            </Link>
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
      )}
    </header>
  );
};
