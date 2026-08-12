const normalizeSkillToken = (value: string) =>
  value.toLowerCase().trim().replace(/\s+/g, "-").replace(/[^a-z0-9-]/g, "");

const extractPathToken = (value: string) => {
  const parts = value.split("/").filter(Boolean);
  const lastPart = parts[parts.length - 1];
  return lastPart ? normalizeSkillToken(lastPart) : "";
};

const getSkillTokenFromValue = (value: unknown): string => {
  if (typeof value === "string") {
    return value.includes("/") ? extractPathToken(value) : normalizeSkillToken(value);
  }

  if (value && typeof value === "object") {
    const skill = value as { index?: unknown; name?: unknown; url?: unknown };

    if (typeof skill.index === "string" && skill.index.trim()) {
      return normalizeSkillToken(skill.index);
    }

    if (typeof skill.name === "string" && skill.name.trim()) {
      return normalizeSkillToken(skill.name);
    }

    if (typeof skill.url === "string" && skill.url.trim()) {
      return extractPathToken(skill.url);
    }
  }

  return "";
};

export const getSkillIconPath = (skill: unknown): string => {
  const token = getSkillTokenFromValue(skill);
  return token ? `/images/card_icons/skills/${token}.png` : "";
};
