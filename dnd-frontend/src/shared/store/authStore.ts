import { create } from "zustand";

interface AuthState {
  isLoggedIn: boolean;
  login: (token: string, refreshToken?: string) => void;
  logout: () => void;
}

export const useAuthStore = create<AuthState>((set) => ({
  isLoggedIn: !!localStorage.getItem("authToken"),
  login: (token, refreshToken) => {
    if (token) {
      localStorage.setItem("authToken", token);
    }
    if (refreshToken) {
      localStorage.setItem("refreshToken", refreshToken);
    }
    set({ isLoggedIn: true });
  },
  logout: () => {
    localStorage.removeItem("authToken");
    localStorage.removeItem("refreshToken");
    
    localStorage.removeItem("userNickname");
    localStorage.removeItem("userEmail");
    set({ isLoggedIn: false });
  },
}));

