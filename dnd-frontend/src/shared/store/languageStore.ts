import { create } from "zustand";
import { persist } from "zustand/middleware";
import i18n from "../../i18n";

interface LangState {
  lang: string,
  toggleLang: (langOption: string) => void,
}


export const useLangStore = create<LangState>()(
  persist(
    (set) => ({
      lang: "EN",
      toggleLang: (option) => {
        i18n.changeLanguage(option.toLowerCase())
        set({ lang: option});
      },
    }),
    {
      name: "language",
    },
  ),
);
