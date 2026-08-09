import { useEffect, useState } from "react";
import { API_BASE_URL } from "../../../shared/api/config";

export const useDetailPageData = (apiCategory: string, itemId: string) => {
  const [data, setData] = useState<any>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      if (!itemId) {
        setError("Item ID is required to load details.");
        setIsLoading(false);
        return;
      }

      try {
        setIsLoading(true);
        setError(null);

        const response = await fetch(`${API_BASE_URL}/api/${apiCategory}/${itemId}`);

        if (!response.ok) {
          throw new Error("Failed to fetch detail data");
        }

        const result = await response.json();
        setData(result);
      } catch (fetchError: any) {
        console.error("Fetch error:", fetchError);
        setError(fetchError?.message || "Failed to load details.");
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [apiCategory, itemId]);

  return { data, isLoading, error };
};
