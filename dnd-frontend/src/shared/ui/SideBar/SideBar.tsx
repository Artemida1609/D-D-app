import { Link } from "react-router-dom";
import { DnDIcon } from "../Icons/DnDIcon";
import { BurgerMenuIcon } from "../Icons/BurgerMenuIcon";
import { navItems } from "../../constants/navItems";
import { ArrowLeft } from "../Icons/ArrowLeft";
import { SearchFilters } from "../SearchFilters/SearchFilters";
import "./SideBar.scss";

export const SideBar = ({
  setActiveAside,
}: {
  setActiveAside: (active: boolean) => void;
}) => {
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
      <div
        className="flex items-center h-[40px] mb-8"
        onClick={() => setActiveAside(false)}
      >
        <span className="cursor-pointer">
          <ArrowLeft />
        </span>
        <span className="ml-6 cursor-pointer text-[#FFFBE4] sidebar__back-text">Search</span>
      </div>

      <SearchFilters variant="sidebar" />

      <div className="mt-auto">
        <div className="sidebar__bottom-nav flex justify-center items-center gap-6 py-6">
          {navItems.map((item) => (
            <Link
              key={item.path}
              to={item.path}
              className="sidebar__bottom-item text-sm text-[#FFFBE4] opacity-80"
            >
              {item.title}
            </Link>
          ))}
        </div>
      </div>
    </aside>
  );
};
