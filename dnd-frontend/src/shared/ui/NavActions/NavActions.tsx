import { Link } from "react-router-dom";
import { ArrowDown } from "../Icons/ArrowDown";
import { useState } from "react";
import "./NavActions.scss";
import { useCollapse } from "../../hooks/useCollapse";
import { useAuthStore } from "../../store/authStore";
import { useLangStore } from "../../store/languageStore";
import { useTranslation } from "react-i18next";

interface NavActionsProps {
  isAside: boolean;
  isTablet?: boolean;
  closeSidebar?: () => void;
}

export const NavActions = ({ isAside, closeSidebar, isTablet }: NavActionsProps) => {
  const language = useLangStore((state) => state.lang);
  const toggleLang = useLangStore((state) => state.toggleLang);
  const [isLangOpen, setIsLangOpen] = useState(false);
  const { ref, height } = useCollapse(isAside && isLangOpen);
  const isLoggedIn = useAuthStore((state) => state.isLoggedIn);
  const { i18n } = useTranslation();

  const handleSelect = (option: string) => {
    toggleLang(option);
    i18n.changeLanguage(option.toLowerCase());
    setIsLangOpen(false);
  };

  return (
    <div className={`flex gap-[24px] nav-actions ${isAside ? "flex-col nav-actions--aside" : "items-center"}`}>
      {isLoggedIn ? (
        <>
          <Link
            to="/favorites"
            className={`flex items-center justify-center login cursor-pointer ${isAside ? "h-auto self-start" : "h-[54px] w-[100px]"}`}
            onClick={closeSidebar}
          >
            Favorites
          </Link>
          <Link
            to="/account"
            className={`flex items-center justify-center signup ${isAside ? "h-auto self-start" : "border border-[#FFFBE4] rounded-[25px] h-[54px] w-[100px]"} cursor-pointer`}
            onClick={closeSidebar}
          >
            Account
          </Link>
        </>
      ) : (
        <>
          <Link
            to="/login"
            className={`flex items-center justify-center login cursor-pointer ${isAside ? "h-auto self-start" : "h-[54px] w-[100px]"}`}
            onClick={closeSidebar}
          >
            Log in
          </Link>
          <Link
            to="/signup"
            className={`flex items-center justify-center signup ${isAside ? "h-auto self-start" : "border border-[#FFFBE4] rounded-[25px] h-[54px] w-[100px]"} cursor-pointer`}
            onClick={closeSidebar}
          >
            Sign up
          </Link>
        </>
      )}

      <div className="language relative">
        {isAside ? (
          <>
            <button
              className="flex items-center gap-4 cursor-pointer self-start mb-4"
              onClick={() => setIsLangOpen((prev) => !prev)}
            >
              {language}
              <ArrowDown className={isLangOpen ? "arrow-up" : "arrow-down"} />
            </button>
            <ul
              ref={ref}
              style={{ height, overflow: "hidden", transition: "height 0.45s cubic-bezier(0.4, 0, 0.2, 1)" }}
              className={`language__dropdown--aside-animated flex flex-col gap-4 ${isTablet ? "pl-6" : "pl-8"}`}
            >
              <li className="language__option self-start"><button onClick={() => handleSelect("EN")}>EN</button></li>
              <li className="language__option self-start"><button onClick={() => handleSelect("UA")}>UA</button></li>
            </ul>
          </>
        ) : (
          <>
            <button className="flex items-start justify-center gap-4 cursor-pointer w-[100px]">
              {language}
              <ArrowDown />
            </button>
            <ul className="language__dropdown">
              <li className="language__option"><button onClick={() => handleSelect("EN")}>EN</button></li>
              <li className="language__option"><button onClick={() => handleSelect("UA")}>UA</button></li>
            </ul>
          </>
        )}
      </div>
    </div>
  );
};
