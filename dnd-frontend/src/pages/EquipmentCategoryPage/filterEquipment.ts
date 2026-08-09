import type { AsyncListItem } from "../AsyncCategoryPage/types/api";

const categoryIndexToCategoryRange: Record<string, string> = {
  "martial-melee-weapons": "Martial Melee",
  "martial-ranged-weapons": "Martial Ranged",
  "martial-weapons": "Martial",
  "melee-weapons": "Melee",
  "ranged-weapons": "Ranged",
  "simple-melee-weapons": "Simple Melee",
  "simple-ranged-weapons": "Simple Ranged",
  "simple-weapons": "Simple",
  "weapon": "Weapon",
  "light-armor": "Light",
  "medium-armor": "Medium",
  "heavy-armor": "Heavy",
  "shield": "Shield",
};

export const filterEquipmentByCategory = (
  items: AsyncListItem[],
  selectedCategory: string | null,
  equipmentType: "weaponCategory" | "armorCategory" | "gearCategory"
): AsyncListItem[] => {
  if (!selectedCategory) return items;

  if (equipmentType === "weaponCategory") {
    const categoryRange = categoryIndexToCategoryRange[selectedCategory];

    return items.filter((item) => {
      if (!categoryRange) return item.equipmentCategoryIndex === selectedCategory;

      if (categoryRange === "Martial") {
        return item.weaponCategory === "Martial";
      }
      if (categoryRange === "Simple") {
        return item.weaponCategory === "Simple";
      }
      if (categoryRange === "Melee") {
        return item.weaponRange === "Melee";
      }
      if (categoryRange === "Ranged") {
        return item.weaponRange === "Ranged";
      }
      if (categoryRange === "Martial Melee") {
        return item.weaponCategory === "Martial" && item.weaponRange === "Melee";
      }
      if (categoryRange === "Martial Ranged") {
        return item.weaponCategory === "Martial" && item.weaponRange === "Ranged";
      }
      if (categoryRange === "Simple Melee") {
        return item.weaponCategory === "Simple" && item.weaponRange === "Melee";
      }
      if (categoryRange === "Simple Ranged") {
        return item.weaponCategory === "Simple" && item.weaponRange === "Ranged";
      }

      return item.equipmentCategoryIndex === selectedCategory;
    });
  }

  if (equipmentType === "armorCategory") {
    const categoryRange = categoryIndexToCategoryRange[selectedCategory];
    return items.filter((item) => {
      if (categoryRange && item.armorCategory) {
        return item.armorCategory.toLowerCase() === categoryRange.toLowerCase();
      }
      return item.equipmentCategoryIndex === selectedCategory;
    });
  }

  if (equipmentType === "gearCategory") {
    return items.filter((item) => {
      const gc = item.gearCategory;
      if (typeof gc === "object" && gc !== null) {
        return (gc as any)?.index === selectedCategory || (gc as any)?.name === selectedCategory;
      }
      return gc === selectedCategory || item.equipmentCategoryIndex === selectedCategory;
    });
  }

  return items;
};
