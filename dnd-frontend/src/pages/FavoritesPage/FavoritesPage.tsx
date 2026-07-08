import { useFavoritesStore } from "../../shared/store/favoritesStore";
import { PageTitle } from "../../shared/ui/PageTitle";
import { CategoryCard } from "../../shared/ui/CategoryCard/CategoryCard";
import { PageBackground } from "../../shared/ui/PageBackground/PageBackground";
import "./FavoritesPage.scss";

export const FavoritesPage = () => {
  const favorites = useFavoritesStore((state) => state.favorites);

  const groupedFavorites = favorites.reduce((acc, item) => {
    const cat = item.category || "Other";
    if (!acc[cat]) {
      acc[cat] = [];
    }
    acc[cat].push(item);
    return acc;
  }, {} as Record<string, typeof favorites>);

  const defaultOrder = ["Character", "Equipment", "Magic", "Bestiary"];
  const displayCategories = Array.from(new Set([...defaultOrder, ...Object.keys(groupedFavorites)]));

  return (
    <>
      <PageBackground variant="favorites" />
      <div className="w-full flex flex-col flex-1 pb-20 relative z-10">
        <PageTitle title="Favorites" />

        {favorites.length === 0 ? (
          <p className="text-[#FFFBE4] text-xl opacity-70 mt-6" style={{ fontFamily: "var(--font-manrope)" }}>
            Your favorites list is empty.
          </p>
        ) : (
          <div className="flex flex-col gap-12 mt-6">
            {displayCategories.map((categoryName) => {
              const items = groupedFavorites[categoryName];
              if (!items || items.length === 0) return null;

              return (
                <div key={categoryName} className="flex flex-col gap-6">
                  <h2 className="text-[#FFFBE4] text-[32px] font-medium" style={{ fontFamily: "var(--font-manrope)" }}>
                    {categoryName}
                  </h2>
                  <div className="flex flex-wrap gap-4 md:gap-[32.5px] justify-start">
                    {items.map((item: any) => (
                      <CategoryCard
                        key={item.id}
                        id={item.id}
                        category={item.category}
                        title={item.title}
                        path={item.path}
                        icon={item.icon || ""}
                      />
                    ))}
                  </div>
                </div>
              );
            })}
          </div>
        )}
      </div>
    </>
  );
};
