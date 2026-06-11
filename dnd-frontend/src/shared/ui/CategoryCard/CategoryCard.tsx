import { Link } from "react-router-dom";
import "./CategoryCard.scss";

interface CategoryCardProps {
  title: string;
  path: string;
  icon: string;
}

export const CategoryCard = ({ title, path, icon }: CategoryCardProps) => {
  return (
    <Link
      to={path}
      className="category-card flex flex-col items-center justify-center gap-6"
    >
      <img 
        src={icon} 
        alt={title} 
        className="w-[100px] h-[100px] object-contain"
      />
      <span className="text-[#FFFBE4] text-[20px] font-medium" style={{ fontFamily: "var(--font-manrope)" }}>
        {title}
      </span>
    </Link>
  );
};
