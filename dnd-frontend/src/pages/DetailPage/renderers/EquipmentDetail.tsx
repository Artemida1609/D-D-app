import React from "react";

export const EquipmentDetail: React.FC<{ data: any }> = ({ data }) => {
  if (!data) return null;

  const cost = data.cost ? `${data.cost.quantity} ${data.cost.unit}` : null;

  if (data.equipment_category?.name === "Armor" || data.armor_category) {
    return (
      <div className="flex flex-col gap-6">
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Armor</h4>
          <p className="opacity-90">{data.name}</p>
          {data.armor_class && (
            <p className="opacity-80">
              Armor Class: {data.armor_class.base}
              {data.armor_class.dex_bonus ? " (+ Dex)" : ""}
            </p>
          )}
          {data.str_minimum !== undefined && (
            <p className="opacity-80">Strength required: {data.str_minimum}</p>
          )}
          {data.stealth_disadvantage !== undefined && (
            <p className="opacity-80">
              Stealth Disadvantage: {data.stealth_disadvantage ? "Yes" : "No"}
            </p>
          )}
          {data.weight !== undefined && (
            <p className="opacity-80">Weight: {data.weight} lb.</p>
          )}
          {cost && <p className="opacity-80">Cost: {cost}</p>}
        </div>
        {Array.isArray(data.desc) &&
          data.desc.map((d: string, i: number) => <p key={i}>{d}</p>)}
        {typeof data.desc === "string" && <p>{data.desc}</p>}
      </div>
    );
  }

  if (data.equipment_category?.name === "Weapon" || data.weapon_category) {
    const dmg = data.damage
      ? `${data.damage.damage_dice}${data.damage.damage_type?.name ? ` ${data.damage.damage_type.name}` : ""}`
      : null;
    const props = Array.isArray(data.properties)
      ? data.properties.map((p: any) => p.name).join(", ")
      : null;

    return (
      <div className="flex flex-col gap-6">
        <h4 className="desc-section-heading text-[#FFFBE4]">Weapon</h4>
        <p className="opacity-90">{data.name}</p>
        {dmg && <p className="opacity-80">Damage: {dmg}</p>}
        {data.range && (
          <p className="opacity-80">
            Range: {data.range.normal}
            {data.range.long ? `/${data.range.long}` : ""} ft.
          </p>
        )}
        {props && <p className="opacity-80">Properties: {props}</p>}
        {data.weight !== undefined && (
          <p className="opacity-80">Weight: {data.weight} lb.</p>
        )}
        {cost && <p className="opacity-80">Cost: {cost}</p>}
        {Array.isArray(data.desc) &&
          data.desc.map((d: string, i: number) => <p key={i}>{d}</p>)}
        {typeof data.desc === "string" && <p>{data.desc}</p>}
      </div>
    );
  }

  return (
    <div className="flex flex-col gap-6">
      <h4 className="desc-section-heading text-[#FFFBE4]">Equipment</h4>
      <p className="opacity-90">{data.name}</p>
      {data.gear_category && (
        <p className="opacity-80">
          Category: {data.gear_category.name || data.gear_category}
        </p>
      )}
      {data.range && (
        <p className="opacity-80">
          Range: {data.range.normal}
          {data.range.long ? `/${data.range.long}` : ""} ft.
        </p>
      )}
      {data.weight !== undefined && (
        <p className="opacity-80">Weight: {data.weight} lb.</p>
      )}
      {cost && <p className="opacity-80">Cost: {cost}</p>}
      {Array.isArray(data.desc) &&
        data.desc.map((d: string, i: number) => <p key={i}>{d}</p>)}
      {typeof data.desc === "string" && <p>{data.desc}</p>}
    </div>
  );
};

export default EquipmentDetail;
