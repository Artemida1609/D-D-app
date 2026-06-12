import { Link } from "react-router-dom";
import { DnDIcon } from "../Icons/DnDIcon";
import { BurgerMenuIcon } from "../Icons/BurgerMenuIcon";
import { navItems } from "../../constants/navItems";
// import { ArrowLeft } from "../Icons/ArrowLeft";
// import { SearchFilters } from "../SearchFilters/SearchFilters";
import "./SideBar.scss";
import { ArrowDown } from "../Icons/ArrowDown";
import { useEffect, useRef, useState } from "react";
import { SearchIcon } from "../Icons/SearchIcon";
import { NavActions } from "../NavActions/NavActions";

export const SideBar = ({
  setActiveAside,
}: {
  setActiveAside: (active: boolean) => void;
}) => {
  const [activeCategory, setActiveCategory] = useState<string | null>(null);

  return (
    <aside className="sidebar absolute w-screen h-screen bg-[#00192D] text-[#FFFBE4] px-4 md:px-10 pb-6 pt-11 z-99 flex flex-col">
      <div className="flex items-center justify-between w-full h-10 mb-8">
        <Link to="/" onClick={() => setActiveAside(false)}>
          <DnDIcon />
        </Link>
        <span className="cursor-pointer" onClick={() => setActiveAside(false)}>
          <BurgerMenuIcon />
        </span>
      </div>
      {/* <div
        className="flex items-center h-[40px] mb-8"
        onClick={() => setActiveAside(false)}
      >
        <span className="cursor-pointer">
          <ArrowLeft />
        </span>
        <span className="ml-6 cursor-pointer text-[#FFFBE4] sidebar__back-text">Search</span>
      </div> */}

      {navItems.map((item) => (
        <ul key={item.title} className="mb-4">
          <li className="flex items-center mb-4 title">
            <Link
              to={item.path}
              className="cursor-pointer flex-1"
              onClick={() => setActiveAside(false)}
            >
              {item.title}
            </Link>
            {item.subItems && (
              <span
                className="cursor-pointer px-2 py-1 ml-4"
                onClick={() =>
                  setActiveCategory(
                    activeCategory === item.title ? null : item.title,
                  )
                }
              >
                <ArrowDown
                  className={activeCategory === item.title ? "arrow-up" : "arrow-down"}
                />
              </span>
            )}
          </li>
          <SubItems 
            item={item} 
            activeCategory={activeCategory} 
            setActiveAside={setActiveAside} 
          />
        </ul>
      ))}

      <div className="w-full h-[1px] bg-[#FFFBE4] mb-6"></div>

      <div className="sidebar__search w-full self-center mb-6">
        <div className="sidebar__search__input-wrapper">
          <div className="sidebar__search__input-inner">
            <button className="sidebar__search__input-icon">
              <SearchIcon />
            </button>
            <input
              type="text"
              placeholder="Search"
              className="sidebar__search__input"
            />
          </div>
        </div>
      </div>

      <div className="w-full h-[1px] bg-[#FFFBE4] mb-6"></div>

      <NavActions isAside={true} closeSidebar={() => setActiveAside(false)} />

      {/* <SearchFilters variant="sidebar" /> */}

    </aside>
  );
};

type SubItem = { title: string; path: string };
type NavItem = { title: string; path: string; subItems?: SubItem[] };

const SubItems = ({
  item,
  activeCategory,
  setActiveAside,
}: {
  item: NavItem;
  activeCategory: string | null;
  setActiveAside: (active: boolean) => void;
}) => {
  const ref = useRef<HTMLUListElement>(null);
  const [height, setHeight] = useState("0px");

  useEffect(() => {
    if (activeCategory === item.title) {
      setHeight(`${ref.current?.scrollHeight}px`);
    } else {
      setHeight("0px");
    }
  }, [activeCategory, item.title]);

  return (
    <ul 
      ref={ref} 
      style={{ height, overflow: "hidden", transition: "height 0.3s ease" }} 
      className="pl-8 sub-items__list"
    >
      {item.subItems?.map((sub: SubItem) => (
        <li key={sub.title} className="mb-4 sub-title">
          <Link
            to={sub.path}
            className="cursor-pointer block"
            onClick={() => setActiveAside(false)}
          >
            {sub.title}
          </Link>
        </li>
      ))}
    </ul>
  );
};