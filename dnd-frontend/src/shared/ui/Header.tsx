export const Header = () => {
  return (
    <header className="absolute top-0 left-0 right-0 z-10 bg-black/60">

      <nav className="font-(family-name:--font-cinzel) text-white text-lg p-4">
        <ul className="flex gap-6 justify-center items-center uppercase tracking-wide text-extrabold">
          {/* а змінити на Link */}
          <li>
            <a href="/">Rules</a>
          </li>
          <li>
            <a href="/about">Library</a>
          </li>
          <li>
            <a href="/contact">Community</a>
          </li>
        </ul>
      </nav>
    </header>
  );
};
