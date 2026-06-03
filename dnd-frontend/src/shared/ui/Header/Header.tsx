import { Link } from 'react-router-dom';
import './Header.scss';

export const Header = () => {
  return (
    <header className="absolute top-0 left-0 right-0 z-10 bg-black/60">
      <nav className="font-(family-name:--font-cinzel) text-white text-lg p-4">
        <ul className="flex gap-6 justify-center items-center uppercase tracking-wide text-extrabold">
          <li>
            <Link to="/">Rules</Link>
          </li>
          <li className="library-menu">
            <div className="cursor-pointer">Library</div>
            <ul className="dropdown">
              <li><span className="disabled">Monsters</span></li>
              <li>
                <Link to="/classes" className="active-link">Classes</Link>
              </li>
              <li><span className="disabled">Races</span></li>
              <li><span className="disabled">Weapons</span></li>
              <li><span className="disabled">Armor</span></li>
            </ul>
          </li>
          <li>
            <Link to="/contact">Community</Link>
          </li>
        </ul>
      </nav>
    </header>
  );
};
