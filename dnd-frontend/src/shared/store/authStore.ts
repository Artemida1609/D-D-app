import { create } from "zustand";

interface AuthState {
  isLoggedIn: boolean;
  toggleAuth: () => void;
}

export const useAuthStore = create<AuthState>((set) => ({
  isLoggedIn: false,
  toggleAuth: () => set((state) => ({ isLoggedIn: !state.isLoggedIn })),
}));
