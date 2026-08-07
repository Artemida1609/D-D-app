import { Link } from "react-router-dom";
import "./Header.scss";
// import { ArrowDown } from "../Icons/ArrowDown";
import { useEffect, useState } from "react";
import { DnDIcon } from "../Icons/DnDIcon";
import { BurgerMenuIcon } from "../Icons/BurgerMenuIcon";
import { navItems } from "../../constants/navItems";
import { SearchFilters } from "../SearchFilters/SearchFilters";
import { NavActions } from "../NavActions/NavActions";
import { NavCategories } from "../NavCategories/NavCategories";
import { useTranslation } from "react-i18next";

export const Header = ({
  setActiveAside,
}: {
  setActiveAside: (active: boolean) => void;
}) => {
  // const [language, setLanguage] = useState("EN");
  const [isMobile, setIsMobile] = useState(() => window.innerWidth < 768);
  const [isTablet, setIsTablet] = useState(() => window.innerWidth < 1350);
  const [isActiveBurgerDropdown, setIsActiveBurgerDropdown] = useState(false);
  const { t } = useTranslation();

  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth < 768);
      setIsTablet(window.innerWidth < 1350);
    };

    handleResize();
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

          <span
            className="cursor-pointer"
            onClick={() => setIsActiveBurgerDropdown((prev) => !prev)}
          >
            <BurgerMenuIcon />
          </span>
          <div
            className={`border border-[#FFFBE4] rounded-[20px] absolute top-[70px] right-0 bg-[#00192D] p-6 mr-6 z-50 ${isActiveBurgerDropdown ? "block" : "hidden"}`}
          >
            <NavCategories isTablet={true} />
            <div className="h-[1px] bg-[#FFFBE4] mb-4"></div>
            <NavActions isAside={true} isTablet={true} />
          </div>
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
                    {item.subItems ? (
                      <span className="cursor-pointer nav-item__link">
                        {t(item.titleKey)}
                      </span>
                    ) : (
                      <Link
                        to={item.path}
                        className="cursor-pointer nav-item__link"
                      >
                        {t(item.titleKey)}
                      </Link>
                    )}
                    {item.subItems && (
                      <ul className="nav-item__dropdown">
                        {item.subItems?.map((subItem) => (
                          <li
                            className="nav-item__dropdown-link"
                            key={subItem.path}
                            onClick={(e) => e.currentTarget.blur()}
                          >
                            <Link to={subItem.path}>{t(subItem.titleKey)}</Link>
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
          <NavActions isAside={false} />
        </>
      )}
    </header>
  );
};

