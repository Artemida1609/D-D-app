import React from "react";

export const SpellDetail: React.FC<{ data: any }> = ({ data }) => {
  if (!data) return null;

  const textOrNull = (value: unknown) => {
    if (typeof value === "string") {
      const trimmed = value.trim();
      return trimmed === "" ? null : trimmed;
    }

    return null;
  };

  const schoolName =
    typeof data.school === "object"
      ? textOrNull(data.school?.name)
      : textOrNull(data.school);
  const hasLevel = data.level !== undefined && data.level !== null;
  const castingTime = textOrNull(data.casting_time);
  const range = textOrNull(data.range);
  const components = Array.isArray(data.components)
    ? data.components
        .filter(
          (component: unknown) =>
            typeof component === "string" && component.trim() !== "",
        )
        .join(", ")
    : textOrNull(data.components) || "";
  const material = textOrNull(data.material);
  const duration = textOrNull(data.duration);
  const durationNotes = [
    data.ritual ? "Ritual" : null,
    data.concentration ? "Concentration" : null,
  ]
    .filter(Boolean)
    .join(" • ");
  const durationValue = [duration, durationNotes].filter(Boolean).join(" • ");

  const description = Array.isArray(data.desc)
    ? data.desc.filter(
        (item: unknown) => typeof item === "string" && item.trim() !== "",
      )
    : typeof data.desc === "string" && data.desc.trim() !== ""
      ? [data.desc]
      : [];
  const higherLevel = Array.isArray(data.higher_level)
    ? data.higher_level.filter(
        (item: unknown) => typeof item === "string" && item.trim() !== "",
      )
    : [];

  const schoolAndLevelItems = [
    schoolName ? schoolName : null,
    hasLevel ? `Level: ${data.level}` : null,
  ].filter(Boolean);
  const castingRows = [
    castingTime ? `Casting Time: ${castingTime}` : null,
    range ? `Range: ${range}` : null,
    components ? `Components: ${components}` : null,
    material ? `Material: ${material}` : null,
    durationValue ? `Duration: ${durationValue}` : null,
  ].filter(Boolean);

  const hasSchoolAndLevel = schoolAndLevelItems.length > 0;
  const hasCasting = castingRows.length > 0;
  const hasDescription = description.length > 0 || higherLevel.length > 0;

  if (!hasSchoolAndLevel && !hasCasting && !hasDescription) {
    return null;
  }

  return (
    <div className="flex flex-col gap-6">
      {hasSchoolAndLevel && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">
            School & Level
          </h4>
          <p className="opacity-90">{schoolAndLevelItems.join(" • ")}</p>
        </div>
      )}

      {hasCasting && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Casting</h4>
          {castingRows.map((row, index) => (
            <p key={index} className="opacity-80">
              {row}
            </p>
          ))}
        </div>
      )}

      {hasDescription && (
        <div>
          <h4 className="desc-section-heading text-[#FFFBE4]">Description</h4>
          {description.map((paragraph: string, index: number) => (
            <p key={index} className="mb-3">
              {paragraph}
            </p>
          ))}
          {higherLevel.map((paragraph: string, index: number) => (
            <p key={`hl-${index}`} className="mb-2">
              {paragraph}
            </p>
          ))}
        </div>
      )}
    </div>
  );
};

export default SpellDetail;
