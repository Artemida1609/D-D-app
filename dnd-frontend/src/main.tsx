import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { App } from "./App";
import "./i18n";
import "./index.css";
import { useLangStore } from "./shared/store/languageStore";
import { transformTranslatedData } from "./shared/utils/translationUtils";
import { apiClient } from "./shared/api/apiClient";

// Intercept global fetch
const originalFetch = window.fetch;
window.fetch = async (...args) => {
  const response = await originalFetch(...args);
  const lang = useLangStore.getState().lang;
  
  if (lang === "UA") {
    const contentType = response.headers.get("content-type");
    if (contentType && contentType.includes("application/json")) {
      // Clone response to avoid body already read errors
      const clonedResponse = response.clone();
      const data = await clonedResponse.json();
      const transformedData = transformTranslatedData(data, lang);
      
      return new Response(JSON.stringify(transformedData), {
        status: response.status,
        statusText: response.statusText,
        headers: response.headers
      });
    }
  }
  return response;
};

// Intercept Axios
apiClient.interceptors.response.use((response) => {
  const lang = useLangStore.getState().lang;
  if (lang === "UA" && response.data) {
    response.data = transformTranslatedData(response.data, lang);
  }
  return response;
});

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <App />
  </StrictMode>,
);
