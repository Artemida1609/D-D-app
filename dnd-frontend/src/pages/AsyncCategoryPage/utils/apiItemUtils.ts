import type { ApiListItem, ApiListResponse } from "../types/api";

export const getItemIdentifier = (item: ApiListItem) => {
  const identifier = item.index ?? item.id;
  if (typeof identifier === "string" && identifier.trim().length > 0) {
    return identifier;
  }

  if (typeof identifier === "number") {
    return String(identifier);
  }

  return undefined;
};

export const toApiItems = (payload: ApiListResponse | ApiListItem[] | null | undefined): ApiListItem[] => {
  if (Array.isArray(payload)) {
    return payload;
  }

  if (!payload) {
    return [];
  }

  return payload.content ?? payload.results ?? payload.data ?? [];
};
