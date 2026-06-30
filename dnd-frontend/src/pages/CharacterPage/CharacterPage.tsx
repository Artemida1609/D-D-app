import { PageTitle } from "../../shared/ui/PageTitle";
import { CategoryCard } from "../../shared/ui/CategoryCard/CategoryCard";

const characterCategories = [
  {
    id: "species",
    title: "Species",
    path: "/character/species",
    icon: "/images/icons/placeholders/character.png",
  },
  {
    id: "classes",
    title: "Classes",
    path: "/character/classes",
    icon: "/images/icons/placeholders/character.png",
  },
  {
    id: "skills",
    title: "Skills",
    path: "/character/skills",
    icon: "/images/icons/placeholders/character.png",
  },
];

export const CharacterPage = () => {
  return (
    <div className="w-full flex flex-col flex-1">
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />

      <PageTitle title="Character" />

      <div className="flex flex-wrap gap-6 justify-start">
        {characterCategories.map((category) => (
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
