import i18n from "i18next";
import { initReactI18next } from "react-i18next";

import en from "./locales/en/translation.json";
import ua from "./locales/ua/translation.json";

const savedLanguage =
  JSON.parse(
    localStorage.getItem("language") || "{}",
  )?.state?.lang?.toLowerCase() || "en";

i18n.use(initReactI18next).init({
  resources: {
    en: {
      translation: en,
    },
    ua: {
      translation: ua,
    },
  },

  lng: savedLanguage,
  fallbackLng: "en",

  interpolation: {
    escapeValue: false,
  },
});

export default i18n;
