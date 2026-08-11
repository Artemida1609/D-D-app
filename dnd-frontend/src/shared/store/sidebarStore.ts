import { create } from "zustand";

interface SidebarState {
  activeAside: boolean;
  openAside: () => void;
  closeAside: () => void;
  setActiveAside: (active: boolean) => void;
}

export const useSidebarStore = create<SidebarState>((set) => ({
  activeAside: false,
  openAside: () => set({ activeAside: true }),
  closeAside: () => set({ activeAside: false }),
  setActiveAside: (active) => set({ activeAside: active }),
}));
