import React from "react";

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
      .map(
        ([key, item]) =>
          `${key.replace(/_/g, " ")}: ${formatDetailValue(item)}`,
      )
      .join(", ");
  }

  return String(value);
};

export const SpeciesDetail: React.FC<{ data: any }> = ({ data }) => {
  if (!data) return null;

  const abilityBonuses = Array.isArray(data?.ability_bonuses)
    ? data.ability_bonuses
        .map(
          (ab: any) =>
            `${ab.ability_score?.name || ab.name || ""} +${ab.bonus}`,
        )
        .join(", ")
    : null;
  const traits = Array.isArray(data?.traits) ? data.traits : [];

  return (
    <div className="flex flex-col gap-6">
      {data.alignment && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Alignment</h4>
          <p className="opacity-90">{data.alignment}</p>
        </div>
      )}

      {data.age && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Age</h4>
          <p className="opacity-90">{data.age}</p>
        </div>
      )}

      {(data.size_description || data.speed) && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Physical</h4>
          <p className="opacity-90">
            {data.size_description}
            {data.speed ? ` • Speed: ${formatDetailValue(data.speed)}` : ""}
          </p>
        </div>
      )}

      {data.language_desc && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Languages</h4>
          <p className="opacity-90">{data.language_desc}</p>
        </div>
      )}

      {abilityBonuses && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">
            Ability Bonuses
          </h4>
          <p className="opacity-90">{abilityBonuses}</p>
        </div>
      )}

      {traits.length > 0 && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Traits</h4>
          {traits.map((t: any, idx: number) => (
            <div key={idx} className="mb-3">
              <h5 className="desc-item-heading text-[#FFFBE4]">{t.name}</h5>
              <p className="opacity-80">{t.desc}</p>
            </div>
          ))}
        </div>
      )}

      {data.starting_proficiencies &&
        data.starting_proficiencies.length > 0 && (
          <div>
            <h4 className="desc-section-heading text-[#FFFBE4]">
              Starting Proficiencies
            </h4>
            <p className="opacity-90">
              {data.starting_proficiencies.map((p: any) => p.name).join(", ")}
            </p>
          </div>
        )}

      {!data.desc && !data.description && (
        <p className="opacity-80">
          No extended description available for this species.
        </p>
      )}

      {Array.isArray(data.desc) &&
        data.desc.map((p: string, i: number) => (
          <p key={i} className="mb-4">
            {p}
          </p>
        ))}
      {typeof data.desc === "string" && <p className="mb-4">{data.desc}</p>}
      {typeof data.description === "string" && (
        <p className="mb-4">{data.description}</p>
      )}
    </div>
  );
};

export default SpeciesDetail;
