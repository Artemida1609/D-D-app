const normalizeSchoolToken = (value: string) =>
  value.toLowerCase().trim().replace(/\s+/g, "");

const extractPathToken = (value: string) => {
  const parts = value.split("/").filter(Boolean);
  const lastPart = parts[parts.length - 1];
  return lastPart ? normalizeSchoolToken(lastPart) : "";
};

const getSchoolTokenFromValue = (value: unknown): string => {
  if (typeof value === "string") {
    return value.includes("/") ? extractPathToken(value) : normalizeSchoolToken(value);
  }

  if (value && typeof value === "object") {
    const school = value as { index?: unknown; name?: unknown; url?: unknown };

    if (typeof school.index === "string" && school.index.trim()) {
      return normalizeSchoolToken(school.index);
    }

    if (typeof school.name === "string" && school.name.trim()) {
      return normalizeSchoolToken(school.name);
    }

    if (typeof school.url === "string" && school.url.trim()) {
      return extractPathToken(school.url);
    }
  }

  return "";
};

export const getSchoolIconPath = (school: unknown): string => {
  const token = getSchoolTokenFromValue(school);
  return token ? `/images/card_icons/schools/${token}.png` : "";
};
