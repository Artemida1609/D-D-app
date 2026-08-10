import React from 'react';
import { PageTitle } from "../../shared/ui/PageTitle";
import { CategoryCard } from "../../shared/ui/CategoryCard/CategoryCard";
import { PageBackground } from "../../shared/ui/PageBackground/PageBackground";

interface ListItem {
  id: string;
  title: string;
  path: string;
  icon: string;
}

interface CategoryListPageProps {
  title: string;
  items: ListItem[];
  backgroundVariant?: "signup" | "login" | "account" | "favorites";
  columns?: number;
}

export const CategoryListPage = ({ title, items, backgroundVariant, columns = 2 }: CategoryListPageProps) => {
  const isMobile = columns <= 2;
  const cardWidth = isMobile ? 172 : 230;
  const gap = isMobile ? 16 : 32;

  const gridStyle: React.CSSProperties = {
    display: 'grid',
    gridTemplateColumns: `repeat(${columns}, minmax(0, 1fr))`,
    columnGap: `${gap}px`,
    rowGap: `${gap}px`,
    justifyContent: 'center',
    width: '100%',
    maxWidth: `${columns * cardWidth + (columns - 1) * gap}px`,
    margin: '0 auto',
    paddingBottom: '20px',
  };

  return (
    <div className="w-full flex flex-col flex-1">
      {backgroundVariant ? (
        <PageBackground variant={backgroundVariant} />
      ) : (
        <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />
      )}

      <PageTitle title={title} />

      <div style={gridStyle}>
        {items.map((item) => (
          <CategoryCard
            key={item.id}
            id={item.id}
            title={item.title}
            path={item.path}
            icon={item.icon}
          />
        ))}
      </div>
    </div>
  );
};

