export const navItems = [
  {
    title: "Character",
    path: "/character",
    subItems: [
      { title: "Species", path: "character/species" },
      { title: "Classes", path: "character/classes" },
      { title: "Skills", path: "character/skills" },
    ],
  },
  {
    title: "Equipment",
    path: "/equipment",
    subItems: [
      { title: "Weapons", path: "equipment/weapons" },
      { title: "Armor", path: "equipment/armor" },
      { title: "Gear", path: "equipment/gear" },
    ],
  },
  {
    title: "Magic",
    path: "/magic",
    subItems: [
      { title: "Spells", path: "magic/spells" },
      { title: "Schools", path: "magic/schools" },
    ],
  },
  {
    title: "Bestiary",
    path: "/bestiary",
  },
];

export const filterCategories = [
  {
    category: "Species",
    subcategories: ["Humanoid", "Beast", "Undead", "Dragon", "Giant"],
  },
  {
    category: "Classes",
    subcategories: ["Warrior", "Mage", "Rogue", "Cleric", "Ranger"],
  },
  {
    category: "Weapons",
    subcategories: ["Melee", "Ranged", "Magic", "Siege"],
  },
  {
    category: "Armors",
    subcategories: ["Light", "Medium", "Heavy", "Shields"],
  },
  {
    category: "Spells",
    subcategories: ["Offensive", "Defensive", "Utility", "Healing"],
  },
];