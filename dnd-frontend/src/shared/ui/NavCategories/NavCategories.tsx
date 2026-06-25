import { navItems } from "../../constants/navItems";
import { Link } from "react-router-dom";
import { ArrowDown } from "../Icons/ArrowDown";
import { useState } from "react";
import "./NavCategories.scss";
import { useCollapse } from "../../hooks/useCollapse";
import { useTranslation } from "react-i18next";

type NavCategoryProps = {
  setActiveAside?: (active: boolean) => void;
  isTablet?: boolean;
};

export const NavCategories = ({
  setActiveAside,
  isTablet,
}: NavCategoryProps) => {
  const [activeCategory, setActiveCategory] = useState<string | null>(null);
  const { t } = useTranslation();

  return (
    <>
      {navItems.map((item) => (
        <ul key={item.titleKey} className={`${isTablet ? "mb-4" : "mb-6"}`}>
          <li className="flex items-center title mb-4">
            <Link
              to={item.path}
              className="cursor-pointer flex-1"
              onClick={() => setActiveAside && setActiveAside(false)}
            >
              {t(item.titleKey)}
            </Link>
            {item.subItems && (
              <span
                className="cursor-pointer px-2 py-1 ml-4"
                onClick={() =>
                  setActiveCategory(
                    activeCategory === item.titleKey ? null : item.titleKey,
                  )
                }
              >
                <ArrowDown
                  className={
                    activeCategory === item.titleKey ? "arrow-up" : "arrow-down"
                  }
                />
              </span>
            )}
          </li>
          <SubItems
            item={item}
            activeCategory={activeCategory}
            setActiveAside={setActiveAside}
            isTablet={isTablet}
          />
        </ul>
      ))}
    </>
  );
};

type SubItem = { titleKey: string; path: string };
type NavItem = { titleKey: string; path: string; subItems?: SubItem[] };

const SubItems = ({
  item,
  activeCategory,
  setActiveAside,
  isTablet,
}: {
  item: NavItem;
  activeCategory: string | null;
  setActiveAside?: (active: boolean) => void;
  isTablet?: boolean;
}) => {
  const isOpen = activeCategory === item.titleKey;
  const { ref, height } = useCollapse(isOpen);
  const { t } = useTranslation();

  return (
    <ul
      ref={ref}
      style={{ height, overflow: "hidden", transition: "height 0.3s ease" }}
      className={`sub-items__list ${isTablet ? "pl-6" : "pl-8"}`}
    >
      {item.subItems?.map((sub) => (
        <li key={sub.titleKey} className="mb-4 sub-title">
          <Link
            to={sub.path}
            className="cursor-pointer block"
            onClick={() => setActiveAside?.(false)}
          >
            {t(sub.titleKey)}
          </Link>
        </li>
      ))}
    </ul>
  );
};
