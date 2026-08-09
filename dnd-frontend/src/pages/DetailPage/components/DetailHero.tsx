import React from "react";
import { Button } from "../../../shared/ui/Button/Button";
import { DetailCharacteristics } from "./DetailCharacteristics";

type DetailHeroProps = {
  title: string;
  imagePath: string;
  fallbackImagePath: string;
  showImageAsContain: boolean;
  isFavorite: boolean;
  onFavoriteClick: () => void;
  characteristics: string[];
};

export const DetailHero: React.FC<DetailHeroProps> = ({
  title,
  imagePath,
  fallbackImagePath,
  showImageAsContain,
  isFavorite,
  onFavoriteClick,
  characteristics,
}) => {
  return (
    <div className="flex flex-col xl:flex-row gap-10 mt-6 w-full items-start">
      <div className="image-container">
        <img
          src={imagePath}
          alt={title}
          loading="eager"
          fetchPriority="high"
          decoding="async"
          width={showImageAsContain ? 160 : 240}
          height={showImageAsContain ? 160 : 240}
          className={showImageAsContain ? "w-[160px] h-[160px] object-contain" : "w-full h-full object-cover"}
          onError={(event) => {
            event.currentTarget.src = fallbackImagePath;
          }}
        />
      </div>

      <div className="flex flex-col gap-8 flex-shrink-0 xl:w-[300px]">
        <h2 className="text-[#FFFBE4] text-[40px] md:text-[48px] font-medium leading-none font-manrope">
          {title}
        </h2>

        <Button
          className={`w-[361px] md:w-[380px] flex items-center justify-center gap-2 px-3 transition-colors ${
            isFavorite ? "favorite-active" : ""
          }`}
          onClick={onFavoriteClick}
        >
          {isFavorite ? "Remove from Favorites" : "Save to Favorites"}
          <svg
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill={isFavorite ? "currentColor" : "none"}
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
          >
            <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path>
          </svg>
        </Button>
      </div>

      <DetailCharacteristics characteristics={characteristics} />
    </div>
  );
};

export default DetailHero;
