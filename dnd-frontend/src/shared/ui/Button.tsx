export const Button = ({
  children,
  className = "",
}: {
  children: React.ReactNode;
  className?: string;
}) => {
  return (
    <button
      className={`h-[64px] rounded-[25px] bg-[#FFFBE4] text-[#00192D] cursor-pointer border-none ${className}`}
    >
      {children}
    </button>
  );
};
