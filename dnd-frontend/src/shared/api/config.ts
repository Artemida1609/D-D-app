const rawApiBaseUrl = import.meta.env.VITE_API_URL || import.meta.env.VITE_APP_URL || "";
export const API_BASE_URL = rawApiBaseUrl.replace(/\/+$|\/$/g, "");
