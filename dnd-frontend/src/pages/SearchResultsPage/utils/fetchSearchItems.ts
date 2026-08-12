import { API_BASE_URL } from "../../../shared/api/config";
import {
  getFavoriteUniqueId,
  getEntityTypeFromPath,
  getFavoritePathFromEntity,
} from "../../../shared/utils/favoritesUtils";
import { getClassIconPath } from "../../../shared/utils/classIcon";
import { getSchoolIconPath } from "../../../shared/utils/schoolIcon";
import { getSkillIconPath } from "../../../shared/utils/skillIcon";
import { buildItemSearchText } from "../../AsyncCategoryPage/utils/searchUtils";
import { toApiItems } from "../../AsyncCategoryPage/utils/apiItemUtils";
import type { ApiListItem, ApiListResponse, AsyncListItem } from "../../AsyncCategoryPage/types/api";

const SEARCH_SOURCES = [
  { endpoint: "/api/races", basePath: "/character/species" },
  { endpoint: "/api/classes", basePath: "/character/classes" },
  { endpoint: "/api/skills", basePath: "/character/skills" },
  { endpoint: "/api/spells", basePath: "/magic/spells" },
  { endpoint: "/api/magic-schools", basePath: "/magic/schools" },
  { endpoint: "/api/monsters", basePath: "/bestiary/monster" },
] as const;

const getPathId = (item: ApiListItem): string => {
  const rawPathId = item.index ?? item.id;
  if (typeof rawPathId === "string" || typeof rawPathId === "number") {
    return String(rawPathId);
  }

  if (typeof item.url === "string") {
    const urlParts = item.url.split("/").filter(Boolean);
    return urlParts[urlParts.length - 1] || "";
  }

  return "";
};

const resolveIcon = (item: ApiListItem, endpoint: string, pathId: string): string => {
  let imagePath = item.image || item.imageUrl || item.icon || "";

  if (typeof imagePath === "string" && imagePath.startsWith("/api/images/")) {
    imagePath = `https://www.dnd5eapi.co${imagePath}`;
  }

  if (pathId && endpoint.includes("/classes")) {
    return getClassIconPath(pathId);
  }

  if (!imagePath && pathId && endpoint.includes("/races")) {
    return `${API_BASE_URL}/api/races/${pathId}/download-image`;
  }

  if (endpoint.includes("/magic-schools") && item.name) {
    return getSchoolIconPath(item.name);
  }

  if (!imagePath && endpoint.includes("/spells")) {
    return getSchoolIconPath(item.school);
  }

  if (endpoint.includes("/skills")) {
    return getSkillIconPath(pathId || item.index || item.name);
  }

  return imagePath;
};

const formatItem = (
  item: ApiListItem,
  endpoint: string,
  basePath: string,
): AsyncListItem | null => {
  const pathId = getPathId(item);
  if (!pathId) {
    return null;
  }

  const entityType = getEntityTypeFromPath(basePath) || "";
  const uniqueId = getFavoriteUniqueId(entityType, pathId);

  return {
    id: uniqueId,
    title: item.name || item.title || "Unknown",
    path: `${basePath}/${pathId}`,
    icon: resolveIcon(item, endpoint, pathId),
    searchText: buildItemSearchText(item),
  };
};

const fetchAllPages = async (endpoint: string): Promise<ApiListItem[]> => {
  const fullEndpoint = `${API_BASE_URL}${endpoint}`;
  const firstResponse = await fetch(`${fullEndpoint}?page=0&size=200`);

  if (!firstResponse.ok) {
    throw new Error(`Failed to fetch ${endpoint}`);
  }

  const firstPayload = (await firstResponse.json()) as ApiListResponse | ApiListItem[];
  const totalPagesFromBackend = Array.isArray(firstPayload) ? 1 : firstPayload.totalPages ?? 1;
  const allData: ApiListItem[] = [...toApiItems(firstPayload)];

  for (let page = 1; page < totalPagesFromBackend; page += 1) {
    const pageResponse = await fetch(`${fullEndpoint}?page=${page}&size=200`);

    if (!pageResponse.ok) {
      throw new Error(`Failed to fetch ${endpoint}`);
    }

    const pagePayload = (await pageResponse.json()) as ApiListResponse | ApiListItem[];
    allData.push(...toApiItems(pagePayload));
  }

  return allData;
};

const fetchEquipmentItems = async (): Promise<AsyncListItem[]> => {
  const allData = await fetchAllPages("/api/equipments");
  const seen = new Set<string>();
  const formattedItems: AsyncListItem[] = [];

  allData.forEach((item) => {
    const pathId = getPathId(item);
    if (!pathId || seen.has(pathId)) {
      return;
    }

    seen.add(pathId);

    let imagePath = item.image || item.imageUrl || item.icon || "";
    if (typeof imagePath === "string" && imagePath.startsWith("/api/images/")) {
      imagePath = `https://www.dnd5eapi.co${imagePath}`;
    }

    formattedItems.push({
      id: getFavoriteUniqueId("equipment", pathId),
      title: item.name || item.title || "Unknown",
      path: getFavoritePathFromEntity("equipment", pathId, item),
      icon: imagePath,
      searchText: buildItemSearchText(item),
    });
  });

  return formattedItems;
};

export const fetchAllSearchItems = async (): Promise<AsyncListItem[]> => {
  const [standardResults, equipmentResults] = await Promise.all([
    Promise.all(
      SEARCH_SOURCES.map(async ({ endpoint, basePath }) => {
        const items = await fetchAllPages(endpoint);
        return items
          .map((item) => formatItem(item, endpoint, basePath))
          .filter((item): item is AsyncListItem => item !== null);
      }),
    ),
    fetchEquipmentItems(),
  ]);

  return [...standardResults.flat(), ...equipmentResults];
};
