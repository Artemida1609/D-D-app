import React from "react";

export const ClassDetail: React.FC<{ data: any }> = ({ data }) => {
  if (!data) return null;

  return (
    <div className="flex flex-col gap-6">
      <div>
        <h4 className="desc-section-heading text-[#FFFBE4]">Class Basics</h4>
        {data.hit_die && <p className="opacity-90">Hit Die: d{data.hit_die}</p>}
        {data.proficiency_choices && data.proficiency_choices.length > 0 && (
          <p className="opacity-90">
            Proficiency choices:{" "}
            {data.proficiency_choices.map((c: any) => c.desc || "").join(" • ")}
          </p>
        )}
      </div>

      {data.proficiencies && data.proficiencies.length > 0 && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Proficiencies</h4>
          <p className="opacity-90">
            {data.proficiencies.map((p: any) => p.name).join(", ")}
          </p>
        </div>
      )}

      {data.saving_throws && data.saving_throws.length > 0 && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Saving Throws</h4>
          <p className="opacity-90">
            {data.saving_throws.map((s: any) => s.name).join(", ")}
          </p>
        </div>
      )}

      {Array.isArray(data.starting_equipment) &&
        data.starting_equipment.length > 0 && (
          <div>
            <h4 className="desc-section-heading text-[#FFFBE4]">
              Starting Equipment
            </h4>
            <p className="opacity-90">
              {data.starting_equipment
                .map((e: any) => e.equipment?.name || e.name)
                .join(", ")}
            </p>
          </div>
        )}

      {Array.isArray(data.desc) &&
        data.desc.map((p: string, i: number) => (
          <p key={i} className="mb-3">
            {p}
          </p>
        ))}
      {typeof data.desc === "string" && <p className="mb-3">{data.desc}</p>}
    </div>
  );
};

export default ClassDetail;
