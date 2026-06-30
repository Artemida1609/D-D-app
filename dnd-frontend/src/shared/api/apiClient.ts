import axios from "axios";

export const apiClient = axios.create({
  baseURL: import.meta.env.VITE_APP_URL ?? "http://localhost:5173",
  headers: { "Content-Type": "application/json" },
});
