export const mockSpecies = Array.from({ length: 10 }, (_, index) => ({
  id: `species-${index}`,
  title: "Species",
  path: `/character/species/${index + 1}`,
  icon: "/images/icons/placeholders/character.png",
}));

export const mockClasses = Array.from({ length: 12 }, (_, index) => ({
  id: `class-${index}`,
  title: "Classes",
  path: `/character/classes/${index + 1}`,
  icon: "/images/icons/placeholders/character.png",
}));

export const mockSkills = Array.from({ length: 8 }, (_, index) => ({
  id: `skill-${index}`,
  title: "Skills",
  path: `/character/skills/${index + 1}`,
  icon: "/images/icons/placeholders/character.png",
}));

export const mockWeapons = Array.from({ length: 15 }, (_, index) => ({
  id: `weapon-${index}`,
  title: "Weapons",
  path: `/equipment/weapons/${index + 1}`,
  icon: "/images/icons/placeholders/equipment.png",
}));

export const mockArmors = Array.from({ length: 10 }, (_, index) => ({
  id: `armor-${index}`,
  title: "Armors",
  path: `/equipment/armors/${index + 1}`,
  icon: "/images/icons/placeholders/equipment.png",
}));

export const mockGear = Array.from({ length: 20 }, (_, index) => ({
  id: `gear-${index}`,
  title: "Gear",
  path: `/equipment/gear/${index + 1}`,
  icon: "/images/icons/placeholders/equipment.png",
}));

export const mockSpells = Array.from({ length: 25 }, (_, index) => ({
  id: `spell-${index}`,
  title: "Spells",
  path: `/magic/spells/${index + 1}`,
  icon: "/images/icons/placeholders/magic.png",
}));

export const mockSchools = Array.from({ length: 8 }, (_, index) => ({
  id: `school-${index}`,
  title: "Schools",
  path: `/magic/schools/${index + 1}`,
  icon: "/images/icons/placeholders/magic.png",
}));

export const mockBestiary = Array.from({ length: 30 }, (_, index) => ({
  id: `monster-${index}`,
  title: "Monster",
  path: `/bestiary/monster/${index + 1}`,
  icon: "/images/icons/placeholders/bestiary.png",
}));
