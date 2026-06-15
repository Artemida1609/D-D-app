import { Link } from "react-router-dom";
import { useFavoritesStore } from "../../store/favoritesStore";
import { HeartIcon } from "../Icons/HeartIcon";
import "./CategoryCard.scss";

interface CategoryCardProps {
  id?: string;
  category?: string;
  title: string;
  path: string;
  icon: string;
}

export const CategoryCard = ({ id, category, title, path, icon }: CategoryCardProps) => {
  const pathParts = path.split("/").filter(Boolean);
  const derivedCategory = category || (pathParts[0] ? pathParts[0].charAt(0).toUpperCase() + pathParts[0].slice(1) : "Unknown");
  const currentId = id || pathParts[pathParts.length - 1] || title;

  const toggleFavorite = useFavoritesStore((state) => state.toggleFavorite);
  const isFavorite = useFavoritesStore((state) => state.isFavorite(currentId));

  const handleFavoriteClick = (e: React.MouseEvent) => {
    e.preventDefault();
    e.stopPropagation();
    toggleFavorite({
      id: currentId,
      title,
      category: derivedCategory,
      path,
    });
  };

  return (
    <Link
      to={path}
      className="category-card group relative flex flex-col items-center justify-center gap-6"
    >
      <button
        onClick={handleFavoriteClick}
        className={`absolute top-4 right-4 z-10 p-1 transition-all duration-300 hover:scale-110 cursor-pointer ${
          isFavorite ? "opacity-100" : "opacity-0 group-hover:opacity-100"
        }`}
      >
        <HeartIcon
          isFilled={isFavorite}
          className="text-[#FFFBE4]"
        />
      </button>

      <img 
        src={icon} 
        alt={title} 
        className="w-[100px] h-[100px] object-contain"
      />
      <span className="text-[#FFFBE4] text-[20px] font-medium" style={{ fontFamily: "var(--font-manrope)" }}>
        {title}
      </span>
    </Link>
  );
};
