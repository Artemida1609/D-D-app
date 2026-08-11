import { API_BASE_URL } from "../../../shared/api/config";
import { getClassIconPath } from "../../../shared/utils/classIcon";
import { getSchoolIconPath } from "../../../shared/utils/schoolIcon";
import { getSkillIconPath } from "../../../shared/utils/skillIcon";

export const apiCategoryMap: Record<string, string> = {
  species: "races",
  race: "races",
  classes: "classes",
  class: "classes",
  skills: "skills",
  weapons: "equipments",
  weapon: "equipments",
  armors: "equipments",
  armor: "equipments",
  gear: "equipments",
  "adventuring-gear": "equipments",
  spells: "spells",
  schools: "magic-schools",
  school: "magic-schools",
  monster: "monsters",
};

const propertyFormatters: Record<string, (val: any) => string | null> = {
  cost: (val) =>
    val?.quantity !== undefined ? `Cost: ${val.quantity} ${val.unit}` : null,
  damage: (val) => {
    if (!val?.damage_dice) return null;
    const dmgType = val.damage_type?.name ? ` ${val.damage_type.name}` : "";
    return `Damage: ${val.damage_dice}${dmgType}`;
  },
  armor_class: (val) => {
    if (val?.base === undefined) return null;
    const dexBonus = val.dex_bonus ? " + Dex" : "";
    return `Armor Class: ${val.base}${dexBonus}`;
  },
  properties: (val) => {
    if (Array.isArray(val) && val.length > 0) {
      const props = val.map((p: any) => p.name).join(", ");
      return `Properties: ${props}`;
    }
    return null;
  },
  range: (val) => {
    if (val?.normal === undefined) return null;
    const longRange = val.long ? `/${val.long}` : "";
    return `Range: ${val.normal}${longRange} ft.`;
  },
};

const capitalizeLabel = (value: string) =>
  value
    .replace(/_/g, " ")
    .replace(/\b\w/g, (char) => char.toUpperCase());

const isApiPath = (value: string) => /^\/api\//i.test(value.trim());

export const resolveApiCategory = (subCategory: string) =>
  apiCategoryMap[subCategory] ||
  (subCategory.endsWith("s") ? subCategory : `${subCategory}s`);

export const getDisplayTitle = (data: any, subCategory: string) =>
  data?.name || data?.title || capitalizeLabel(subCategory);

export const resolveDetailImage = (
  data: any,
  apiCategory: string,
  itemId: string,
  mainCategory: string,
) => {
  let fetchedImage = data?.image || data?.imageUrl || data?.icon || "";

  if (fetchedImage.startsWith("/api/images/")) {
    fetchedImage = `https://www.dnd5eapi.co${fetchedImage}`;
  }

  if (itemId && apiCategory === "classes") {
    fetchedImage = getClassIconPath(itemId);
  } else if (!fetchedImage && itemId && apiCategory === "races") {
    fetchedImage = `${API_BASE_URL}/api/${apiCategory}/${itemId}/download-image`;
  } else if (!fetchedImage && apiCategory === "spells") {
    fetchedImage = getSchoolIconPath(data?.school);
  } else if (apiCategory === "magic-schools" && data?.name) {
    fetchedImage = getSchoolIconPath(data?.name);
  } else if (apiCategory === "skills") {
    fetchedImage = getSkillIconPath(itemId || data?.index || data?.name);
  }

  return {
    fetchedImage,
    imagePath:
      fetchedImage || `/images/icons/placeholders/${mainCategory.toLowerCase()}.png`,
  };
};

export const getDetailImageFallback = (mainCategory: string) =>
  `/images/icons/placeholders/${mainCategory.toLowerCase()}.png`;

export const buildCharacteristics = (data: any) => {
  const dynamicCharacteristics: string[] = [];
  const maxCharacteristicLength = 95;
  const addCharacteristic = (value: string) => {
    if (value.length <= maxCharacteristicLength) {
      dynamicCharacteristics.push(value);
    }
  };

  if (!data) return [];

  const ignoreKeys = [
    "_id",
    "index",
    "url",
    "name",
    "title",
    "desc",
    "description",
    "image",
    "imageUrl",
    "icon",
    "special_abilities",
    "actions",
    "legendary_actions",
    "equipment_category",
    "weapon_category",
    "weapon_range",
    "armor_category",
    "gear_category",
    "age",
    "alignment",
    "size_description",
    "language_desc",
    "traits",
    "starting_proficiencies",
    "contents",
    "special",
    "category_range",
    "capacity",
    "quantity",
    "speed",
    "stealth_disadvantage",
    "str_minimum",
    "throw_range",
    "tool_category",
    "vehicle_category",
    "two_handed_damage",
  ];

  for (const [key, value] of Object.entries(data)) {
    if (ignoreKeys.includes(key) || value == null) continue;

    const formattedKey = capitalizeLabel(key);

    if (typeof value === "string" || typeof value === "number") {
      if (typeof value === "string" && isApiPath(value)) {
        continue;
      }

      if (key === "weight") {
        addCharacteristic(`${formattedKey}: ${value} lb.`);
      } else {
        addCharacteristic(`${formattedKey}: ${value}`);
      }
    } else if (Array.isArray(value) && value.length > 0) {
      if (value.every((v) => typeof v === "string" || typeof v === "number")) {
        addCharacteristic(`${formattedKey}: ${value.join(", ")}`);
      } else if (value[0]?.name) {
        addCharacteristic(
          `${formattedKey}: ${value.map((v: any) => v.name).join(", ")}`,
        );
      }
    } else if (typeof value === "object" && value !== null) {
      const formatter = propertyFormatters[key];
      if (formatter) {
        const formattedString = formatter(value);
        if (formattedString) addCharacteristic(formattedString);
      } else if ((value as any).name) {
        addCharacteristic(`${formattedKey}: ${(value as any).name}`);
      } else {
        addCharacteristic(formattedKey);
      }
    }
  }

  return dynamicCharacteristics.slice(0, 8);
};

export const hasMagicSchoolDescription = (data: any) =>
  (Array.isArray(data?.description) && data.description.length > 0) ||
  (typeof data?.description === "string" && data.description.trim() !== "") ||
  (Array.isArray(data?.desc) && data.desc.length > 0) ||
  (typeof data?.desc === "string" && data.desc.trim() !== "");

export const hasSpellDescription = (data: any) =>
  (typeof data?.school === "string" && data.school.trim() !== "") ||
  (typeof data?.school?.name === "string" && data.school.name.trim() !== "") ||
  data?.level !== undefined ||
  (typeof data?.casting_time === "string" && data.casting_time.trim() !== "") ||
  (typeof data?.range === "string" && data.range.trim() !== "") ||
  (Array.isArray(data?.components) && data.components.length > 0) ||
  (typeof data?.material === "string" && data.material.trim() !== "") ||
  (typeof data?.duration === "string" && data.duration.trim() !== "") ||
  (Array.isArray(data?.desc) && data.desc.length > 0) ||
  (typeof data?.desc === "string" && data.desc.trim() !== "") ||
  (Array.isArray(data?.higher_level) && data.higher_level.length > 0);

export const shouldShowDescription = (apiCategory: string, data: any) => {
  const hasDescription = data?.desc || data?.description;

  if (apiCategory === "equipments") {
    return Boolean(hasDescription);
  }

  if (apiCategory === "spells") {
    return hasSpellDescription(data);
  }

  if (apiCategory === "magic-schools") {
    return hasMagicSchoolDescription(data);
  }

  return true;
};
