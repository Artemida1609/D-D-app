import { useEffect, useMemo, useState } from "react";
import { Link } from "react-router-dom";
import { useFavoritesStore } from "../../store/favoritesStore";
import { HeartIcon } from "../Icons/HeartIcon";
import { getOptimizedCardImageUrl } from "../../api/optimizeImageUrl";
import { getEntityIdFromPath, getEntityTypeFromPath } from "../../utils/favoritesUtils";
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
  const optimizedIcon = useMemo(() => getOptimizedCardImageUrl(icon), [icon]);
  const [imageSrc, setImageSrc] = useState(optimizedIcon || icon);

  useEffect(() => {
    setImageSrc(optimizedIcon || icon);
  }, [optimizedIcon, icon]);

  const toggleFavorite = useFavoritesStore((state) => state.toggleFavorite);
  const isFavorite = useFavoritesStore((state) => state.isFavorite(currentId));

  const handleFavoriteClick = (e: React.MouseEvent) => {
    e.preventDefault();
    e.stopPropagation();

    const entityType = getEntityTypeFromPath(path);
    const entityId = getEntityIdFromPath(path);

    toggleFavorite({
      id: currentId,
      title,
      category: derivedCategory,
      path,
      icon,
      entityType,
      entityId,
    });
  };

  return (
    <Link to={path} className="category-card group relative flex flex-col items-center">
      <div className="category-card__image-wrapper">
        {icon && (
          <>
            <img
              src={imageSrc}
              alt={title}
              loading="lazy"
              decoding="async"
              fetchPriority="low"
              onError={() => {
                if (imageSrc !== icon) setImageSrc(icon);
              }}
              className="category-card__image"
            />

            <button
              onClick={handleFavoriteClick}
              aria-label="Toggle favorite"
              className={`category-card__favorite ${isFavorite ? "favorite--active" : "favorite--inactive"}`}
            >
              <HeartIcon isFilled={isFavorite} className="text-[#FFFBE4]" />
            </button>
          </>
        )}
      </div>

      <span className="category-card__title">
        {title}
      </span>
    </Link>
  );
};
