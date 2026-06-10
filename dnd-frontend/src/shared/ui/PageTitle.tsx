import { useNavigate } from "react-router-dom";

interface PageTitleProps {
  title: string;
}

export const PageTitle = ({ title }: PageTitleProps) => {
  const navigate = useNavigate();

  return (
    <div className="flex items-center gap-6 mt-[80px] mb-[48px] h-[68px]">
      <button onClick={() => navigate(-1)} className="cursor-pointer">
        <img 
          src="/images/icons/back-arrow.svg" 
          alt="Back" 
          className="w-[40px] h-[40px]"
        />
      </button>
      <h1 className="text-[#FFFBE4] text-[46px] uppercase font-serif tracking-widest leading-none">
        {title}
      </h1>
    </div>
  );
};
