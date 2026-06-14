import { PageTitle } from "../../shared/ui/PageTitle";
import { Button } from "../../shared/ui/Button/Button";
import { useLocation } from "react-router-dom";
import { useFavoritesStore } from "../../shared/store/favoritesStore";

export const DetailPage = () => {
  // const { id } = useParams();
  // const params = useParams<{ id: string }>();
  const location = useLocation();

  const pathParts = location.pathname.split("/").filter(Boolean);
  const mainCategory = pathParts[0] || "character";
  const subCategory = (pathParts[1] || "category").toLowerCase();
  const currentId = location.pathname;

  const displayTitle = subCategory.charAt(0).toUpperCase() + subCategory.slice(1);
  const imagePath = `/images/icons/placeholders/${mainCategory.toLowerCase()}.png`;

  const toggleFavorite = useFavoritesStore((state) => state.toggleFavorite);
  const isFavorite = useFavoritesStore((state) => state.isFavorite(currentId));

  const handleFavoriteClick = () => {
    toggleFavorite({
      id: currentId,
      title: `${displayTitle} ${currentId}`,
      category: mainCategory.charAt(0).toUpperCase() + mainCategory.slice(1),
      path: location.pathname,
    });
  };

  const characteristics = [
    "Characteristic: Lorem ipsum",
    "Characteristic: Lorem ipsum",
    "Characteristic: Lorem ipsum",
    "Characteristic: Lorem ipsum",
  ];

  return (
    <div className="w-full flex flex-col flex-1 pb-20">
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />

      <PageTitle title={displayTitle} />

      <div className="flex flex-col xl:flex-row gap-10 mt-6 w-full items-start">
        <div className="w-[240px] h-[240px] flex-shrink-0 border border-[#FFFBE4] rounded-[24px] flex items-center justify-center bg-[rgba(255,255,255,0.05)]">
          <img
            src={imagePath}
            alt={displayTitle}
            className="w-[160px] h-[160px] object-contain"
            onError={(e) => {
              e.currentTarget.src = "/images/icons/placeholders/character.png";
            }}
          />
        </div>

        <div className="flex flex-col gap-8 flex-shrink-0 xl:w-[300px]">
          <h2 className="text-[#FFFBE4] text-[40px] md:text-[48px] font-medium leading-none" style={{ fontFamily: "var(--font-manrope)" }}>
            {displayTitle}
          </h2>
          
          <Button 
            className={`w-full md:w-[380px] flex items-center justify-center gap-2 transition-colors ${
              isFavorite ? "!bg-[#EF4444] !border-[#EF4444]" : ""
            }`}
            onClick={handleFavoriteClick}
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

        <div className="flex flex-col gap-4 xl:ml-10">
          {characteristics.map((char, index) => (
            <div 
              key={index} 
              className="flex items-center px-6 py-4 border border-[rgba(255,251,228,0.2)] rounded-[30px] text-[#FFFBE4] text-[16px] md:text-[18px] w-full md:w-[498px] flex-shrink-0" 
              style={{ fontFamily: "var(--font-manrope)" }}
            >
              {char}
            </div>
          ))}
        </div>
      </div>

      <div className="flex flex-col mt-16 gap-6">
        <h3 className="text-[#FFFBE4] text-[32px] font-medium" style={{ fontFamily: "var(--font-manrope)" }}>
          Description
        </h3>
        <div className="text-[#FFFBE4] text-[16px] md:text-[18px] leading-relaxed opacity-90" style={{ fontFamily: "var(--font-manrope)" }}>
          <p className="mb-6">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras imperdiet maximus iaculis. Sed quis gravida nulla. Curabitur pulvinar nec elit vitae porttitor. Donec eget gravida est, eu accumsan est. Etiam at malesuada tortor, eget vulputate ipsum. Nunc elementum diam eget ipsum interdum congue. Nam dignissim elit at augue mollis dictum. Nam sit amet mattis ipsum. Suspendisse libero leo, viverra eu nisl vitae, molestie pulvinar ligula. Donec nec ornare justo. Suspendisse non condimentum nisi.
          </p>
          <p>
            Mauris auctor nunc vitae nibh pulvinar, at feugiat libero gravida. Proin imperdiet, nisl vel pretium blandit, ex libero eleifend odio, at fermentum risus dui in dui. Suspendisse dui orci, faucibus nec laoreet id, rhoncus nec dolor. Duis suscipit orci id est convallis, non ultrices diam posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus sit amet ornare metus. Donec porttitor condimentum tellus in suscipit. Duis luctus libero neque, eget egestas metus pulvinar nec. Phasellus orci leo, dignissim ultrices convallis vitae, euismod sed tortor.
          </p>
        </div>
      </div>
    </div>
  );
};
