import React from "react";

export const DefaultDetail: React.FC<{ data: any }> = ({ data }) => {
  if (!data) return null;

  const descData = data.desc || data.description;

  if (Array.isArray(descData) && descData.length > 0) {
    return <>{descData.map((p: string, i: number) => <p key={i} className="mb-4">{p}</p>)}</>;
  }

  if (typeof descData === 'string' && descData.trim() !== '') {
    return <p>{descData}</p>;
  }

  
  const keysToShow = ['desc', 'description', 'index', 'type', 'size', 'age'];
  const entries = Object.entries(data).filter(([k, v]) => keysToShow.includes(k) && v != null);

  if (entries.length > 0) {
    return (
      <div className="flex flex-col gap-2">
        {entries.map(([k, v]) => (
          <div key={k}>
            <strong className="text-[#FFFBE4]">{k.replace('_',' ')}: </strong>
            <span className="opacity-80">{typeof v === 'object' ? JSON.stringify(v) : String(v)}</span>
          </div>
        ))}
      </div>
    );
  }

  return <p className="opacity-80">No detailed description available.</p>;
};

export default DefaultDetail;

