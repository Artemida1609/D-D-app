import { Link } from "react-router-dom";
import "./Footer.scss";
import { navItems } from "../../constants/navItems";
import { useTranslation } from "react-i18next";

export const Footer = () => {
  const { t } = useTranslation();
  return (
    <footer className="w-full px-20 pb-[34px] md:pb-[40px] pt-10 z-10">
      <ul className="flex flex-wrap justify-center items-center gap-8 md:gap-16">
        {navItems.map((item) => (
          <li key={item.path}>
            <Link to={item.path} className="footer__link">
              {t(item.titleKey)}
            </Link>
          </li>
        ))}
      </ul>
    </footer>
  );
};
