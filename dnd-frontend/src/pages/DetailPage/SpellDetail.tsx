import React from "react";

export const SpellDetail: React.FC<{ data: any }> = ({ data }) => {
  if (!data) return null;

  return (
    <div className="flex flex-col gap-6">
      <div>
        <h4 className="desc-section-heading text-[#FFFBE4]">School & Level</h4>
        <p className="opacity-90">{data.school?.name || data.school} • Level: {data.level}</p>
      </div>

      <div>
        <h4 className="desc-section-heading text-[#FFFBE4]">Casting</h4>
        <p className="opacity-80">Casting Time: {data.casting_time}</p>
        <p className="opacity-80">Range: {data.range}</p>
        <p className="opacity-80">Components: {Array.isArray(data.components) ? data.components.join(', ') : data.components}</p>
        {data.material && <p className="opacity-80">Material: {data.material}</p>}
        <p className="opacity-80">Duration: {data.duration}{data.ritual ? ' • Ritual' : ''}{data.concentration ? ' • Concentration' : ''}</p>
      </div>

      <div>
        <h4 className="desc-section-heading text-[#FFFBE4]">Description</h4>
        {Array.isArray(data.desc) && data.desc.map((p: string, i: number) => <p key={i} className="mb-3">{p}</p>)}
        {typeof data.desc === 'string' && <p className="mb-3">{data.desc}</p>}
        {Array.isArray(data.higher_level) && data.higher_level.map((p: string, i: number) => <p key={`hl-${i}`} className="mb-2">{p}</p>)}
      </div>
    </div>
  );
};

export default SpellDetail;
