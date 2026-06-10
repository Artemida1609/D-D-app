import { Link } from "react-router-dom";
import "./Footer.scss";

export const Footer = () => {
  return (
    <footer className="w-full pb-[34px] md:pb-[40px] pt-10 z-10">
      <ul className="flex flex-wrap justify-center items-center gap-8 md:gap-16">
        <li>
          <Link to="/character" className="footer__link">
            Character
          </Link>
        </li>
        <li>
          <Link to="/equipment" className="footer__link">
            Equipment
          </Link>
        </li>
        <li>
          <Link to="/magic" className="footer__link">
            Magic
          </Link>
        </li>
        <li>
          <Link to="/bestiary" className="footer__link">
            Bestiary
          </Link>
        </li>
      </ul>
    </footer>
  );
};
