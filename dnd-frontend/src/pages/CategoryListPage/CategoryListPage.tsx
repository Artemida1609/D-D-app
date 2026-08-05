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
  // decide card width and gap based on columns (keeps layout consistent with AsyncCategoryPage)
  const isMobile = columns <= 2;
  const cardWidth = isMobile ? 172 : 230; // outer card widths
  const gap = isMobile ? 16 : 32; // md gap vs mobile gap (32 fits 3 cols in 754)

  const gridStyle: React.CSSProperties = {
    display: 'grid',
    gridTemplateColumns: `repeat(${columns}, ${cardWidth}px)`,
    columnGap: `${gap}px`,
    rowGap: `${gap}px`,
    justifyContent: 'center',
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
