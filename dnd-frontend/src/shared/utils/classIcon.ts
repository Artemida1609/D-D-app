export const getClassIconPath = (classIndex?: string | null): string => {
  const normalizedIndex = classIndex?.trim().toLowerCase();
  return normalizedIndex ? `/images/card_icons/classes/${normalizedIndex}.png` : "";
};
