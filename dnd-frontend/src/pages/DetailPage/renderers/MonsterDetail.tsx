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

export const MonsterDetail: React.FC<{ data: any }> = ({ data }) => {
  if (!data) return null;

  const stats = [
    "strength",
    "dexterity",
    "constitution",
    "intelligence",
    "wisdom",
    "charisma",
  ].map((s) => ({ k: s, v: data[s] }));

  return (
    <div className="flex flex-col gap-6">
      <div>
        <h4 className="desc-section-heading text-[#FFFBE4]">Basic Info</h4>
        <p className="opacity-90">
          {data.size} {data.type}
          {data.subtype ? ` (${data.subtype})` : ""} • {data.alignment}
        </p>
        {data.challenge_rating !== undefined && (
          <p className="opacity-80">CR: {data.challenge_rating}</p>
        )}
      </div>

      <div>
        <h4 className="desc-section-heading text-[#FFFBE4]">Combat</h4>
        {data.hit_points !== undefined && (
          <p className="opacity-80">
            Hit Points: {data.hit_points} ({data.hit_dice || data.hit_dice})
          </p>
        )}
        {data.armor_class !== undefined && (
          <p className="opacity-80">
            Armor Class:{" "}
            {Array.isArray(data.armor_class)
              ? (data.armor_class[0]?.value ??
                formatDetailValue(data.armor_class))
              : data.armor_class}
          </p>
        )}
        {data.speed && (
          <p className="opacity-80">
            Speed:{" "}
            {typeof data.speed === "object"
              ? Object.entries(data.speed)
                  .map(([k, v]) => `${k} ${v}`)
                  .join(", ")
              : data.speed}
          </p>
        )}
      </div>

      <div>
        <h4 className="desc-section-heading text-[#FFFBE4]">Statistics</h4>
        <div className="grid grid-cols-3 gap-2">
          {stats.map((s) => (
            <div key={s.k} className="opacity-80">
              {s.k.charAt(0).toUpperCase() + s.k.slice(1)}: {s.v ?? "-"}
            </div>
          ))}
        </div>
      </div>

      {Array.isArray(data.senses) || data.senses ? (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Senses</h4>
          <p className="opacity-80">{formatDetailValue(data.senses)}</p>
        </div>
      ) : null}

      {data.languages && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Languages</h4>
          <p className="opacity-80">{data.languages}</p>
        </div>
      )}

      {Array.isArray(data.special_abilities) &&
        data.special_abilities.length > 0 && (
          <div>
            <h4 className="desc-section-heading text-[#FFFBE4]">
              Special Abilities
            </h4>
            {data.special_abilities.map((a: any, i: number) => (
              <div key={i} className="mb-3">
                <h5 className="desc-item-heading text-[#FFFBE4]">{a.name}</h5>
                <p className="opacity-80">{a.desc}</p>
              </div>
            ))}
          </div>
        )}

      {Array.isArray(data.actions) && data.actions.length > 0 && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Actions</h4>
          {data.actions.map((a: any, i: number) => (
            <div key={i} className="mb-3">
              <h5 className="desc-item-heading text-[#FFFBE4]">{a.name}</h5>
              <p className="opacity-80">{a.desc}</p>
            </div>
          ))}
        </div>
      )}

      {Array.isArray(data.legendary_actions) &&
        data.legendary_actions.length > 0 && (
          <div>
            <h4 className="desc-section-heading text-[#FFFBE4]">
              Legendary Actions
            </h4>
            {data.legendary_actions.map((a: any, i: number) => (
              <div key={i} className="mb-3">
                <h5 className="desc-item-heading text-[#FFFBE4]">{a.name}</h5>
                <p className="opacity-80">{a.desc}</p>
              </div>
            ))}
          </div>
        )}

      {Array.isArray(data.desc) &&
        data.desc.map((p: string, i: number) => (
          <p key={i} className="mb-4">
            {p}
          </p>
        ))}
      {typeof data.desc === "string" && <p className="mb-4">{data.desc}</p>}
    </div>
  );
};

export default MonsterDetail;
