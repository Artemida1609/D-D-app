import React from "react";
import SpeciesDetail from "./SpeciesDetail";
import EquipmentDetail from "./EquipmentDetail";
import MonsterDetail from "./MonsterDetail";
import SpellDetail from "./SpellDetail";
import ClassDetail from "./ClassDetail";
import DefaultDetail from "./DefaultDetail";

export const DetailRenderer: React.FC<{ apiCategory: string; data: any }> = ({ apiCategory, data }) => {
  const cat = (apiCategory || '').toLowerCase();

  if (cat === 'races' || cat === 'species') {
    return <SpeciesDetail data={data} />;
  }

  if (cat === 'equipment') {
    return <EquipmentDetail data={data} />;
  }

  if (cat === 'monsters' || cat === 'monster') {
    return <MonsterDetail data={data} />;
  }

  if (cat === 'spells' || cat === 'magic-schools' || cat === 'magic_schools') {
    return <SpellDetail data={data} />;
  }

  if (cat === 'classes') {
    return <ClassDetail data={data} />;
  }

  return <DefaultDetail data={data} />;
};

export default DetailRenderer;
