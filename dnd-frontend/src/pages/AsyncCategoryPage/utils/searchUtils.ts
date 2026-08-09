import { filterKeywordsMap } from "./filterMapping";
import type { ApiListItem } from "../types/api";

export const normalizeText = (value: string) => value.toLowerCase().trim();

export const tokenFromFilter = (filterKey: string) => filterKey.split(".").pop() || filterKey;

export const buildItemSearchText = (item: ApiListItem) => {
  const textParts = [
    item.name,
    item.title,
    item.index,
    item.id,
    item.url,
    item.path,
    item.type,
    item.category,
    item.description,
    item.fullName,
    item.slug,
  ].filter((value): value is string => typeof value === "string" && value.trim().length > 0);

  return normalizeText([JSON.stringify(item), ...textParts].join(" "));
};

export const matchesSubcategory = (filterKey: string, searchText: string) => {
  const token = tokenFromFilter(filterKey);
  const keywords = filterKeywordsMap[token] || [token];
  return keywords.some((keyword) => searchText.includes(keyword));
};
