import { create } from "zustand";
import { persist } from "zustand/middleware";
import { API_BASE_URL } from "../api/config";
import {
  getFavoriteCategoryFromEntityType,
  getFavoritePathFromEntity,
  getFavoriteUniqueId,
} from "../utils/favoritesUtils";

export interface FavoriteItem {
  id: string;
  title: string;
  category: string;
  path: string;
  icon?: string;
  savedId?: number;
  entityType?: string;
  entityId?: string | number;
}

interface FavoritesState {
  favorites: FavoriteItem[];
  toggleFavorite: (item: FavoriteItem) => void;
  isFavorite: (id: string) => boolean;
  loadFavorites: () => Promise<void>;
  clearFavorites: () => void;
}

const getAuthToken = () => localStorage.getItem("authToken");

const getAuthHeaders = (): HeadersInit | undefined => {
  const token = getAuthToken();
  if (!token) {
    return undefined;
  }

  return {
    Authorization: `Bearer ${token}`,
  };
};

const loadSavedElements = async () => {
  const headers = getAuthHeaders();
  if (!headers) {
    throw new Error("No auth token available");
  }

  const response = await fetch(`${API_BASE_URL}/saved`, {
    headers,
  });

  if (!response.ok) {
    throw new Error("Failed to load saved favorites");
  }

  return response.json() as Promise<Array<{ id: number; entityType: string; entityId: number }>>;
};

const addSavedElement = async (entityType: string, entityId: number) => {
  const headers = getAuthHeaders();
  if (!headers) {
    throw new Error("No auth token available");
  }

  const response = await fetch(`${API_BASE_URL}/saved/add`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      ...headers,
    },
    body: JSON.stringify({ entityType, entityId }),
  });

  if (!response.ok) {
    const error = await response.json().catch(() => null);
    throw new Error(error?.message || error?.error || "Failed to save favorite");
  }

  return response.json() as Promise<{ id: number; entityType: string; entityId: number }>;
};

const removeSavedElement = async (savedId: number) => {
  const headers = getAuthHeaders();
  if (!headers) {
    throw new Error("No auth token available");
  }

  const response = await fetch(`${API_BASE_URL}/saved/${savedId}`, {
    method: "DELETE",
    headers,
  });

  if (!response.ok) {
    throw new Error("Failed to remove saved favorite");
  }
};

const normalizeEntityApiPath = (entityType: string): string => {
  switch (entityType) {
    case "equipment":
      return "equipments";
    default:
      return entityType;
  }
};

const fetchEntityDetails = async (entityType: string, entityId: string | number) => {
  const path = normalizeEntityApiPath(entityType);
  const response = await fetch(`${API_BASE_URL}/api/${path}/${entityId}`);
  if (!response.ok) {
    throw new Error("Failed to fetch favorite entity details");
  }
  return response.json();
};

export const useFavoritesStore = create<FavoritesState>()(
  persist(
    (set, get) => ({
      favorites: [],
      toggleFavorite: async (item) => {
        const currentFavorites = get().favorites;
        const exists = currentFavorites.some((fav) => fav.id === item.id);

        if (exists) {
          const existing = currentFavorites.find((fav) => fav.id === item.id);
          if (existing?.savedId && getAuthToken()) {
            try {
              await removeSavedElement(existing.savedId);
            } catch (error) {
              console.error("Failed to remove saved favorite:", error);
            }
          }
          set({ favorites: currentFavorites.filter((fav) => fav.id !== item.id) });
          return;
        }

        const nextItem = { ...item };
        const authToken = getAuthToken();
        if (authToken && item.entityType && item.entityId !== undefined) {
          const entityIdNumber = Number(item.entityId);
          if (!Number.isNaN(entityIdNumber)) {
            try {
              const savedResponse = await addSavedElement(item.entityType, entityIdNumber);
              nextItem.savedId = savedResponse.id;
            } catch (error) {
              console.error("Failed to persist favorite to backend:", error);
            }
          }
        }

        set({ favorites: [...currentFavorites, nextItem] });
      },
      isFavorite: (id) => get().favorites.some((fav) => fav.id === id),
      clearFavorites: () => {
        set({ favorites: [] });
      },
      loadFavorites: async () => {
        const authToken = getAuthToken();
        if (!authToken) {
          return;
        }

        try {
          const savedElements = await loadSavedElements();
          const fetchedFavorites = await Promise.all(
            savedElements.map(async (saved) => {
              try {
                const details = await fetchEntityDetails(saved.entityType, saved.entityId);
                const title = details?.name || details?.title || `${saved.entityType} ${saved.entityId}`;
                const icon = details?.image || details?.imageUrl || details?.icon || "";
                const category = getFavoriteCategoryFromEntityType(saved.entityType);
                const path = getFavoritePathFromEntity(saved.entityType, saved.entityId, details);
                return {
                  id: getFavoriteUniqueId(saved.entityType, saved.entityId),
                  title,
                  category,
                  path,
                  icon,
                  savedId: saved.id,
                  entityType: saved.entityType,
                  entityId: String(saved.entityId),
                } as FavoriteItem;
              } catch (error) {
                console.error("Failed to load saved favorite details:", error);
                return null;
              }
            }),
          );

          const validFavorites = fetchedFavorites.filter(
            (item): item is FavoriteItem => item !== null,
          );

          const localFavorites = get().favorites;
          const mergedFavorites = [...validFavorites];

          localFavorites.forEach((local) => {
            if (!mergedFavorites.some((fav) => fav.id === local.id)) {
              mergedFavorites.push(local);
            }
          });

          set({ favorites: mergedFavorites });
        } catch (error) {
          console.error("Failed to load favorites:", error);
        }
      },
    }),
    {
      name: "dnd-favorites",
    },
  ),
);


