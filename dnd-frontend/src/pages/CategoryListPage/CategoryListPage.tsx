import { PageTitle } from "../../shared/ui/PageTitle";
import { CategoryCard } from "../../shared/ui/CategoryCard/CategoryCard";

interface ListItem {
  id: string;
  title: string;
  path: string;
  icon: string;
}

interface CategoryListPageProps {
  title: string;
  items: ListItem[];
}

export const CategoryListPage = ({ title, items }: CategoryListPageProps) => {
  return (
    <div className="w-full flex flex-col flex-1">
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />

      <PageTitle title={title} />

      <div className="flex flex-wrap gap-6 justify-start pb-20">
        {items.map((item) => (
          <CategoryCard
            key={item.id}
            title={item.title}
            path={item.path}
            icon={item.icon}
          />
        ))}
      </div>
    </div>
  );
};
