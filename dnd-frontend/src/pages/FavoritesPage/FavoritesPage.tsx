import { useFavoritesStore } from "../../shared/store/favoritesStore";
import { PageTitle } from "../../shared/ui/PageTitle";
import { CategoryCard } from "../../shared/ui/CategoryCard/CategoryCard";
import "./FavoritesPage.scss";

export const FavoritesPage = () => {
  const favorites = useFavoritesStore((state) => state.favorites);

  const groupedFavorites = favorites.reduce((acc, item) => {
    if (!acc[item.category]) {
      acc[item.category] = [];
    }
    acc[item.category].push(item);
    return acc;
  }, {} as Record<string, typeof favorites>);

  const categoriesOrder = ["Character", "Equipment", "Magic", "Bestiary"];

  return (
    <div className="w-full flex flex-col flex-1 pb-20">
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />

      <PageTitle title="Favorites" />

      {favorites.length === 0 ? (
        <p className="text-[#FFFBE4] text-xl opacity-70 mt-6" style={{ fontFamily: "var(--font-manrope)" }}>
          Your favorites list is empty.
        </p>
      ) : (
        <div className="flex flex-col gap-12 mt-6">
          {categoriesOrder.map((categoryName) => {
            const items = groupedFavorites[categoryName];
            if (!items || items.length === 0) return null;

            return (
              <div key={categoryName} className="flex flex-col gap-6">
                <h2 className="text-[#FFFBE4] text-[32px] font-medium" style={{ fontFamily: "var(--font-manrope)" }}>
                  {categoryName}
                </h2>
                <div className="flex flex-wrap gap-6 justify-start">
                  {items.map((item) => {
                    const iconPath = `/images/icons/placeholders/${item.category.toLowerCase()}.png`;

                    return (
                      <CategoryCard
                        key={item.id}
                        title={item.title}
                        path={item.path}
                        icon={iconPath}
                      />
                    );
                  })}
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};
