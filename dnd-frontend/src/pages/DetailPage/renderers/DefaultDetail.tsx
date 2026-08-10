import React from "react";

const formatLabel = (value: string) =>
  value.replace(/_/g, " ").replace(/\b\w/g, (char) => char.toUpperCase());

const formatDetailValue = (value: any): string => {
  if (value == null) return "";

  if (Array.isArray(value)) {
    return value
      .map((item) => formatDetailValue(item))
      .filter(Boolean)
      .join(", ");
  }

  if (typeof value === "object") {
    if (typeof value.name === "string") {
      return value.name;
    }

    return Object.entries(value)
      .map(([key, item]) => `${formatLabel(key)}: ${formatDetailValue(item)}`)
      .join(", ");
  }

  return String(value);
};

export const DefaultDetail: React.FC<{ data: any }> = ({ data }) => {
  if (!data) return null;

  const descData = data.desc || data.description;

  if (Array.isArray(descData) && descData.length > 0) {
    return (
      <>
        {descData.map((p: string, i: number) => (
          <p key={i} className="mb-4">
            {p}
          </p>
        ))}
      </>
    );
  }

  if (typeof descData === "string" && descData.trim() !== "") {
    return <p>{descData}</p>;
  }

  const keysToShow = ["desc", "description", "index", "type", "size", "age"];
  const entries = Object.entries(data).filter(
    ([k, v]) => keysToShow.includes(k) && v != null,
  );

  if (entries.length > 0) {
    return (
      <div className="flex flex-col gap-2">
        {entries.map(([k, v]) => (
          <div key={k}>
            <h5 className="desc-item-heading text-[#FFFBE4]">
              {formatLabel(k)}
            </h5>
            <p className="opacity-80">{formatDetailValue(v)}</p>
          </div>
        ))}
      </div>
    );
  }

  return <p className="opacity-80">No detailed description available.</p>;
};

export default DefaultDetail;
