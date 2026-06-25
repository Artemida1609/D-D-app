import { PageTitle } from "../../shared/ui/PageTitle";
import { CategoryCard } from "../../shared/ui/CategoryCard/CategoryCard";

const magicCategories = [
  {
    id: "spells",
    title: "Spells",
    path: "/magic/spells",
    icon: "/images/icons/placeholders/magic.png",
  },
  {
    id: "schools",
    title: "Schools",
    path: "/magic/schools",
    icon: "/images/icons/placeholders/magic.png",
  },
];

export const MagicPage = () => {
  return (
    <div className="w-full flex flex-col flex-1">
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />

      <PageTitle title="Magic" />

      <div className="flex flex-wrap gap-6 justify-start">
        {magicCategories.map((category) => (
          <CategoryCard
            key={category.id}
            title={category.title}
            path={category.path}
            icon={category.icon}
          />
        ))}
      </div>
    </div>
  );
};
