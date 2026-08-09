import React from "react";

export const MagicSchoolDetail: React.FC<{ data: any }> = ({ data }) => {
  if (!data) return null;

  const description = Array.isArray(data.description)
    ? data.description.filter(
        (item: unknown) => typeof item === "string" && item.trim() !== "",
      )
    : typeof data.description === "string" && data.description.trim() !== ""
      ? [data.description]
      : Array.isArray(data.desc)
        ? data.desc.filter(
            (item: unknown) => typeof item === "string" && item.trim() !== "",
          )
        : typeof data.desc === "string" && data.desc.trim() !== ""
          ? [data.desc]
          : [];

  if (description.length === 0) {
    return null;
  }

  return (
    <div className="flex flex-col gap-6">
      {description.map((paragraph: string, index: number) => (
        <p key={index} className="mb-3">
          {paragraph}
        </p>
      ))}
    </div>
  );
};

export default MagicSchoolDetail;
