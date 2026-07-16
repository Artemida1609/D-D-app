import { create } from "zustand";
import { filterCategories } from "../constants/filterCategories";

interface SearchState {
  query: string;
  activeCategory: string;
  chosenSubcategories: string[];
  setQuery: (query: string) => void;
  setActiveCategory: (category: string) => void;
  addSubcategory: (subcategory: string) => void;
  removeSubcategory: (subcategory: string) => void;
  toggleSubcategory: (subcategory: string) => void;
  clearFilters: () => void;
}

const initialCategory = filterCategories[0]?.categoryKey || "filters.species";

export const useSearchStore = create<SearchState>((set) => ({
  query: "",
  activeCategory: initialCategory,
  chosenSubcategories: [],

  setQuery: (query) => set({ query }),
  setActiveCategory: (category) => set({ activeCategory: category }),

  addSubcategory: (subcategory) =>
    set((state) =>
      state.chosenSubcategories.includes(subcategory)
        ? state
        : { chosenSubcategories: [...state.chosenSubcategories, subcategory] }
    ),

  removeSubcategory: (subcategory) =>
    set((state) => ({
      chosenSubcategories: state.chosenSubcategories.filter((value) => value !== subcategory),
    })),

  toggleSubcategory: (subcategory) =>
    set((state) => ({
      chosenSubcategories: state.chosenSubcategories.includes(subcategory)
        ? state.chosenSubcategories.filter((value) => value !== subcategory)
        : [...state.chosenSubcategories, subcategory],
    })),

  clearFilters: () => set({ query: "", chosenSubcategories: [] }),
}));
