import { Link } from "react-router-dom";
import { ArrowDown } from "../Icons/ArrowDown";
import { useState } from "react";
import "./NavActions.scss"; 

export const NavActions = ({ isAside }: { isAside: boolean }) => {
    const [language, setLanguage] = useState("EN");
  
  return (
    <div className={`flex gap-[24px] nav-actions ${isAside ? "flex-col nav-actions--aside" : "items-center"}`}>
      <Link
        to="/login"
        className={`flex items-center justify-center login cursor-pointer ${isAside ? "h-auto self-start" : "h-[54px] w-[100px]"}`}
      >
        Log in
      </Link>
      <Link
        to="/signup"
        className={`flex items-center justify-center signup ${isAside ? "h-auto self-start" : "border border-[#FFFBE4] rounded-[25px] h-[54px] w-[100px]"} cursor-pointer `}
      >
        Sign up
      </Link>
      <div className="language relative">
        <button className={`flex items-start justify-center gap-4 cursor-pointer ${isAside ? "self-start" : "w-[100px]"}`}>
          {language}
          <ArrowDown />
        </button>
        <ul className={`language__dropdown ${isAside ? "language__dropdown--aside" : ""}`}>
          <li className="language__option">
            <button onClick={() => setLanguage("EN")}>EN</button>
          </li>
          <li className="language__option">
            <button onClick={() => setLanguage("UKR")}>UKR</button>
          </li>
        </ul>
      </div>
    </div>
  );
};
