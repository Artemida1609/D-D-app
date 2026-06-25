import { PageTitle } from "../../shared/ui/PageTitle";
import { CategoryCard } from "../../shared/ui/CategoryCard/CategoryCard";

const equipmentCategories = [
  {
    id: "weapons",
    title: "Weapons",
    path: "/equipment/weapons",
    icon: "/images/icons/placeholders/equipment.png",
  },
  {
    id: "armors",
    title: "Armors",
    path: "/equipment/armors",
    icon: "/images/icons/placeholders/equipment.png",
  },
  {
    id: "gear",
    title: "Gear",
    path: "/equipment/gear",
    icon: "/images/icons/placeholders/equipment.png",
  },
];

export const EquipmentPage = () => {
  return (
    <div className="w-full flex flex-col flex-1">
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />

      <PageTitle title="Equipment" />

      <div className="flex flex-wrap gap-6 justify-start">
        {equipmentCategories.map((category) => (
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
