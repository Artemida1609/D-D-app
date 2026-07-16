export const Button = ({
  children,
  className = "",
  onClick,
}: {
  children: React.ReactNode;
  className?: string;
  onClick?: () => void;
}) => {
  return (
    <button
      type="button"
      onClick={onClick}
      className={`h-[64px] rounded-[25px] bg-[#FFFBE4] text-[#00192D] cursor-pointer border-none ${className}`}
    >
      {children}
    </button>
  );
};
