import { useLocation, useParams } from "react-router-dom";
import { PageTitle } from "../../shared/ui/PageTitle";
import { useFavoritesStore } from "../../shared/store/favoritesStore";
import { getFavoriteUniqueId } from "../../shared/utils/favoritesUtils";
import "./DetailPage.scss";
import { DetailRenderer } from "./renderers";
import {
  buildCharacteristics,
  getDetailImageFallback,
  getDisplayTitle,
  resolveApiCategory,
  resolveDetailImage,
  shouldShowDescription,
} from "./utils/detailPageUtils";
import { useDetailPageData } from "./hooks/useDetailPageData";
import { DetailHero } from "./components/DetailHero";

export const DetailPage = () => {
  const location = useLocation();
  const params = useParams<{ id: string }>();

  const pathParts = location.pathname.split("/").filter(Boolean);
  const mainCategory = pathParts[0] || "character";
  const subCategory = (pathParts[1] || "category").toLowerCase();
  const itemId = params.id || "";
  const apiCategory = resolveApiCategory(subCategory);
  const currentId = getFavoriteUniqueId(apiCategory, itemId);

  const { data, isLoading, error } = useDetailPageData(apiCategory, itemId);
  const displayTitle = getDisplayTitle(data, subCategory);
  const { fetchedImage, imagePath } = resolveDetailImage(
    data,
    apiCategory,
    itemId,
    mainCategory,
  );
  const fallbackImagePath = getDetailImageFallback(mainCategory);
  const characteristicsToDisplay = buildCharacteristics(data);
  const toggleFavorite = useFavoritesStore((state) => state.toggleFavorite);
  const isFavorite = useFavoritesStore((state) => state.isFavorite(currentId));

  const handleFavoriteClick = () => {
    toggleFavorite({
      id: currentId,
      title: displayTitle,
      category: mainCategory.charAt(0).toUpperCase() + mainCategory.slice(1),
      path: location.pathname,
      icon: fetchedImage,
      entityType: apiCategory,
      entityId: itemId,
    });
  };

  const showDescriptionSection = shouldShowDescription(apiCategory, data);

  if (isLoading) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        Loading...
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        <div className="text-center">
          <p className="text-lg font-medium">{error}</p>
          <p className="opacity-80 mt-2">
            Please try again later or return to another section.
          </p>
        </div>
      </div>
    );
  }

  if (!data) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        <p className="text-lg font-medium">
          No details were found for this item.
        </p>
      </div>
    );
  }

  return (
    <div className="detail-page w-full flex flex-col flex-1 pb-20">
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />

      <PageTitle title={displayTitle} />

      <DetailHero
        title={displayTitle}
        imagePath={imagePath}
        fallbackImagePath={fallbackImagePath}
        showImageAsContain={!fetchedImage}
        isFavorite={isFavorite}
        onFavoriteClick={handleFavoriteClick}
        characteristics={characteristicsToDisplay}
      />

      {showDescriptionSection && (
        <div className="flex flex-col mt-16 gap-6 w-full max-w-[1000px]">
          <h3 className="desc-heading text-[#FFFBE4]">Description</h3>
          <div className="description-text text-[#FFFBE4]">
            <DetailRenderer apiCategory={apiCategory} data={data} />
          </div>
        </div>
      )}
    </div>
  );
};
