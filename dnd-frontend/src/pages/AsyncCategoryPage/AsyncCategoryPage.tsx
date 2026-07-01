import { useEffect, useState } from "react";
import { CategoryListPage } from "../CategoryListPage/CategoryListPage";

interface AsyncCategoryPageProps {
  title: string;
  endpoint: string;
  basePath: string;
  backgroundVariant?: "signup" | "login" | "account" | "bestiary" | "favorites";
}

export const AsyncCategoryPage = ({ title, endpoint, basePath, backgroundVariant }: AsyncCategoryPageProps) => {
  const [items, setItems] = useState<any[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setIsLoading(true);
        
        const fetchUrl = endpoint.includes("?") 
          ? `${endpoint}&size=40` 
          : `${endpoint}?size=40`;

        const response = await fetch(fetchUrl);
        
        if (!response.ok) {
          throw new Error(`Failed to fetch ${title}`);
        }

        const data = await response.json();

        let dataArray = [];
        if (Array.isArray(data)) {
          dataArray = data;
        } else if (data && Array.isArray(data.content)) {
          dataArray = data.content;
        } else if (data && Array.isArray(data.results)) {
          dataArray = data.results;
        } else if (data && data.data && Array.isArray(data.data)) {
          dataArray = data.data;
        } else {
          dataArray = [];
        }

        const formattedItems = dataArray.map((item: any) => {
          let imagePath = item.image || item.imageUrl || item.url || item.icon || "";
          
          if (imagePath.startsWith("/api/images/")) {
            imagePath = `https://www.dnd5eapi.co${imagePath}`;
          }

          return {
            id: item.index || item.id || Math.random().toString(),
            title: item.name || item.title || "Unknown",
            path: `${basePath}/${item.index || item.id}`,
            icon: imagePath || "/images/bg/hero-bg/hero-bg-desktop.jpg", 
          };
        });

        setItems(formattedItems);
      } catch (error) {
        console.error("Error fetching data:", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [endpoint, basePath, title]);

  if (isLoading) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-white min-h-[50vh]">
        Loading {title}...
      </div>
    );
  }

  return (
    <CategoryListPage 
      title={title} 
      items={items} 
      backgroundVariant={backgroundVariant} 
    />
  );
};
