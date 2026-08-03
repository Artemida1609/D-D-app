import { PageTitle } from "../../shared/ui/PageTitle";
import { CategoryCard } from "../../shared/ui/CategoryCard/CategoryCard";
import { PageBackground } from "../../shared/ui/PageBackground/PageBackground";

interface ListItem {
  id: string;
  title: string;
  path: string;
  icon: string;
}

interface CategoryListPageProps {
  title: string;
  items: ListItem[];
  backgroundVariant?: "signup" | "login" | "account" | "favorites";
}

export const CategoryListPage = ({ title, items, backgroundVariant }: CategoryListPageProps) => {
  return (
    <div className="w-full flex flex-col flex-1">
      {backgroundVariant ? (
        <PageBackground variant={backgroundVariant} />
      ) : (
        <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />
      )}

      <PageTitle title={title} />

      <div className="flex flex-wrap gap-4 md:gap-[32.5px] justify-start pb-20">
        {items.map((item) => (
          <CategoryCard
            key={item.id}
            id={item.id}
            title={item.title}
            path={item.path}
            icon={item.icon}
          />
        ))}
      </div>
    </div>
  );
};
