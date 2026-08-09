import React from "react";

type DetailCharacteristicsProps = {
  characteristics: string[];
};

export const DetailCharacteristics: React.FC<DetailCharacteristicsProps> = ({
  characteristics,
}) => {
  if (characteristics.length === 0) return null;

  const useTwoColumns = characteristics.length > 5;
  const spanLast = useTwoColumns && characteristics.length % 2 !== 0;

  return (
    <div
      className={`detail-characteristics xl:ml-auto w-full xl:self-start ${
        useTwoColumns ? "detail-characteristics--two-columns" : ""
      }`}
    >
      {characteristics.map((char, index) => (
        <div
          key={index}
          className={`characteristic-card ${
            spanLast && index === characteristics.length - 1 ? "characteristic-card--span-two" : ""
          }`}
        >
          {char}
        </div>
      ))}
    </div>
  );
};

export default DetailCharacteristics;
