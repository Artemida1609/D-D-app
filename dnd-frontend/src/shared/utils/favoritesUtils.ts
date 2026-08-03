export const getEntityIdFromPath = (path: string): string | undefined => {
  const parts = path.split("/").filter(Boolean);
  return parts.length > 0 ? parts[parts.length - 1] : undefined;
};

export const getEntityTypeFromPath = (path: string): string | undefined => {
  const normalizedPath = path.toLowerCase();

  if (normalizedPath.startsWith("/character/species")) {
    return "races";
  }
  if (normalizedPath.startsWith("/character/classes")) {
    return "classes";
  }
  if (normalizedPath.startsWith("/character/skills")) {
    return "skills";
  }
  if (
    normalizedPath.startsWith("/equipment/weapons") ||
    normalizedPath.startsWith("/equipment/armors") ||
    normalizedPath.startsWith("/equipment/gear")
  ) {
    return "equipment";
  }
  if (normalizedPath.startsWith("/magic/spells")) {
    return "spells";
  }
  if (normalizedPath.startsWith("/magic/schools")) {
    return "magic-schools";
  }
  if (normalizedPath.startsWith("/bestiary/monster")) {
    return "monsters";
  }

  return undefined;
};

export const getFavoriteCategoryFromEntityType = (entityType: string): string => {
  switch (entityType) {
    case "races":
    case "classes":
    case "skills":
      return "Character";
    case "equipment":
      return "Equipment";
    case "spells":
    case "magic-schools":
      return "Magic";
    case "monsters":
      return "Bestiary";
    default:
      return "Other";
  }
};

export const getFavoritePathFromEntity = (
  entityType: string,
  entityId: string | number,
  details?: any,
): string => {
  const id = String(entityId);

  switch (entityType) {
    case "races":
      return `/character/species/${id}`;
    case "classes":
      return `/character/classes/${id}`;
    case "skills":
      return `/character/skills/${id}`;
    case "spells":
      return `/magic/spells/${id}`;
    case "magic-schools":
      return `/magic/schools/${id}`;
    case "monsters":
      return `/bestiary/monster/${id}`;
    case "equipment": {
      const category = details?.equipment_category?.name?.toLowerCase();
      if (category?.includes("weapon")) {
        return `/equipment/weapons/${id}`;
      }
      if (category?.includes("armor")) {
        return `/equipment/armors/${id}`;
      }
      return `/equipment/gear/${id}`;
    }
    default:
      return `/${id}`;
  }
};

export const getFavoriteUniqueId = (entityType: string, entityId: string | number): string => {
  return `${entityType}-${String(entityId)}`;
};
