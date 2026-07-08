import { create } from "zustand";
import { persist } from "zustand/middleware";

export interface FavoriteItem {
  id: string;
  title: string;
  category: string; 
  path: string;
  icon?: string;
}

interface FavoritesState {
  favorites: FavoriteItem[];
  toggleFavorite: (item: FavoriteItem) => void;
  isFavorite: (id: string) => boolean;
}

export const useFavoritesStore = create<FavoritesState>()(
  persist(
    (set, get) => ({
      favorites: [],
      toggleFavorite: (item) => {
        const currentFavorites = get().favorites;
        const exists = currentFavorites.some((fav) => fav.id === item.id);
        if (exists) {
          set({ favorites: currentFavorites.filter((fav) => fav.id !== item.id) });
        } else {
          set({ favorites: [...currentFavorites, item] });
        }
      },
      isFavorite: (id) => get().favorites.some((fav) => fav.id === id),
    }),
    {
      name: "dnd-favorites", 
    }
  )
);
