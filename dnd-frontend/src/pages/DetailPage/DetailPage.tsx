import { useEffect, useState } from "react";
import { PageTitle } from "../../shared/ui/PageTitle";
import { Button } from "../../shared/ui/Button/Button";
import { useLocation, useParams } from "react-router-dom";
import { useFavoritesStore } from "../../shared/store/favoritesStore";
import { API_BASE_URL } from "../../shared/api/config";
import { getFavoriteUniqueId } from "../../shared/utils/favoritesUtils";
import { getClassIconPath } from "../../shared/utils/classIcon";
import "./DetailPage.scss";
import { DetailRenderer } from "./renderers";

const propertyFormatters: Record<string, (val: any) => string | null> = {
  cost: (val) =>
    val?.quantity !== undefined ? `Cost: ${val.quantity} ${val.unit}` : null,
  damage: (val) => {
    if (!val?.damage_dice) return null;
    const dmgType = val.damage_type?.name ? ` ${val.damage_type.name}` : "";
    return `Damage: ${val.damage_dice}${dmgType}`;
  },
  armor_class: (val) => {
    if (val?.base === undefined) return null;
    const dexBonus = val.dex_bonus ? " + Dex" : "";
    return `Armor Class: ${val.base}${dexBonus}`;
  },
  properties: (val) => {
    if (Array.isArray(val) && val.length > 0) {
      const props = val.map((p: any) => p.name).join(", ");
      return `Properties: ${props}`;
    }
    return null;
  },
  range: (val) => {
    if (val?.normal === undefined) return null;
    const longRange = val.long ? `/${val.long}` : "";
    return `Range: ${val.normal}${longRange} ft.`;
  },
};

export const DetailPage = () => {
  const location = useLocation();
  const params = useParams<{ id: string }>();
  const [data, setData] = useState<any>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const pathParts = location.pathname.split("/").filter(Boolean);
  const mainCategory = pathParts[0] || "character";
  const subCategory = (pathParts[1] || "category").toLowerCase();
  const itemId = params.id || "";
  const apiCategoryMap: Record<string, string> = {
    species: "races",
    race: "races",
    classes: "classes",
    class: "classes",
    skills: "skills",
    weapons: "equipments",
    weapon: "equipments",
    armors: "equipments",
    armor: "equipments",
    gear: "equipments",
    "adventuring-gear": "equipments",
    spells: "spells",
    schools: "magic-schools",
    school: "magic-schools",
    monster: "monsters",
  };

  const apiCategory =
    apiCategoryMap[subCategory] ||
    (subCategory.endsWith("s") ? subCategory : `${subCategory}s`);
  const currentId = getFavoriteUniqueId(apiCategory, itemId);

  useEffect(() => {
    const fetchData = async () => {
      if (!itemId) {
        setError("Item ID is required to load details.");
        setIsLoading(false);
        return;
      }

      try {
        setIsLoading(true);
        setError(null);

        const fetchUrl = `${API_BASE_URL}/api/${apiCategory}/${itemId}`;

        const response = await fetch(fetchUrl);

        if (!response.ok) {
          throw new Error("Failed to fetch detail data");
        }

        const result = await response.json();
        setData(result);
      } catch (fetchError: any) {
        console.error("Fetch error:", fetchError);
        setError(fetchError?.message || "Failed to load details.");
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [itemId, subCategory]);

  const displayTitle =
    data?.name ||
    data?.title ||
    subCategory.charAt(0).toUpperCase() + subCategory.slice(1);

  let fetchedImage = data?.image || data?.imageUrl || data?.icon || "";
  if (fetchedImage.startsWith("/api/images/")) {
    fetchedImage = `https://www.dnd5eapi.co${fetchedImage}`;
  }
  
  if (itemId && apiCategory === "classes") {
    fetchedImage = getClassIconPath(itemId);
  } else if (!fetchedImage && itemId && apiCategory === "races") {
    fetchedImage = `${API_BASE_URL}/api/${apiCategory}/${itemId}/download-image`;
  }
  
  const imagePath =
    fetchedImage ||
    `/images/icons/placeholders/${mainCategory.toLowerCase()}.png`;

  const toggleFavorite = useFavoritesStore((state) => state.toggleFavorite);
  const isFavorite = useFavoritesStore((state) => state.isFavorite(currentId));

  const handleFavoriteClick = () => {
    toggleFavorite({
      id: currentId,
      title: displayTitle,
      category: mainCategory.charAt(0).toUpperCase() + mainCategory.slice(1),
      path: location.pathname,
      icon: fetchedImage,
      entityType: apiCategory,
      entityId: itemId,
    });
  };

  const dynamicCharacteristics: string[] = [];
  if (data) {
    const ignoreKeys = [
      "_id",
      "index",
      "url",
      "name",
      "title",
      "desc",
      "description",
      "image",
      "imageUrl",
      "icon",
      "special_abilities",
      "actions",
      "legendary_actions",
      "equipment_category",
      "weapon_category",
      "weapon_range",
      "armor_category",
      "gear_category",
      "age",
      "alignment",
      "size_description",
      "language_desc",
      "traits",
      "starting_proficiencies",
      "contents",
      "special",
      "category_range",
      "capacity",
      "quantity",
      "speed",
      "stealth_disadvantage",
      "str_minimum",
      "throw_range",
      "tool_category",
      "vehicle_category",
      "two_handed_damage",
    ];

    for (const [key, value] of Object.entries(data)) {
      if (ignoreKeys.includes(key) || value == null) continue;

      const formattedKey = key
        .replace(/_/g, " ")
        .replace(/\b\w/g, (l) => l.toUpperCase());

      if (typeof value === "string" || typeof value === "number") {
        if (key === "weight") {
          dynamicCharacteristics.push(`${formattedKey}: ${value} lb.`);
        } else {
          dynamicCharacteristics.push(`${formattedKey}: ${value}`);
        }
      } else if (Array.isArray(value) && value.length > 0) {
        if (
          value.every((v) => typeof v === "string" || typeof v === "number")
        ) {
          dynamicCharacteristics.push(`${formattedKey}: ${value.join(", ")}`);
        } else if (value[0]?.name) {
          dynamicCharacteristics.push(
            `${formattedKey}: ${value.map((v: any) => v.name).join(", ")}`,
          );
        }
      } else if (typeof value === "object" && value !== null) {
        const formatter = propertyFormatters[key];
        if (formatter) {
          const formattedString = formatter(value);
          if (formattedString) dynamicCharacteristics.push(formattedString);
        } else if ((value as any).name) {
          dynamicCharacteristics.push(
            `${formattedKey}: ${(value as any).name}`,
          );
        } else {
          dynamicCharacteristics.push(formattedKey);
        }
      }
    }
  }

  const characteristicsToDisplay =
    dynamicCharacteristics.length > 0
      ? dynamicCharacteristics.slice(0, 8)
      : ["No additional characteristics"];

  const renderDescription = () => {
    return <DetailRenderer apiCategory={apiCategory} data={data} />;
  };

  const isEquipment = apiCategory === "equipments";
  const hasDescription = data?.desc || data?.description;
  const showDescriptionSection = !isEquipment || hasDescription;

  if (isLoading) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        Loading...
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        <div className="text-center">
          <p className="text-lg font-medium">{error}</p>
          <p className="opacity-80 mt-2">
            Please try again later or return to another section.
          </p>
        </div>
      </div>
    );
  }

  if (!data) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        <p className="text-lg font-medium">
          No details were found for this item.
        </p>
      </div>
    );
  }

  return (
    <div className="detail-page w-full flex flex-col flex-1 pb-20">
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />

      <PageTitle title={displayTitle} />

      <div className="flex flex-col xl:flex-row gap-10 mt-6 w-full items-start">
        <div className="image-container">
          {fetchedImage ? (
            <img
              src={imagePath}
              alt={displayTitle}
              loading="eager"
              fetchPriority="high"
              decoding="async"
              width={240}
              height={240}
              className="w-full h-full object-cover"
              onError={(e) => {
                e.currentTarget.src = `/images/icons/placeholders/${mainCategory.toLowerCase()}.png`;
              }}
            />
          ) : (
            <img
              src={imagePath}
              alt={displayTitle}
              loading="eager"
              fetchPriority="high"
              decoding="async"
              width={160}
              height={160}
              className="w-[160px] h-[160px] object-contain"
            />
          )}
        </div>

        <div className="flex flex-col gap-8 flex-shrink-0 xl:w-[300px]">
          <h2 className="text-[#FFFBE4] text-[40px] md:text-[48px] font-medium leading-none font-manrope">
            {displayTitle}
          </h2>

          <Button
            className={`w-[361px] md:w-[380px] flex items-center justify-center gap-2 px-3 transition-colors ${
              isFavorite ? "favorite-active" : ""
            }`}
            onClick={handleFavoriteClick}
          >
            {isFavorite ? "Remove from Favorites" : "Save to Favorites"}
            <svg
              width="24"
              height="24"
              viewBox="0 0 24 24"
              fill={isFavorite ? "currentColor" : "none"}
              stroke="currentColor"
              strokeWidth="2"
              strokeLinecap="round"
              strokeLinejoin="round"
            >
              <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path>
            </svg>
          </Button>
        </div>

        <div className="flex flex-col gap-4 xl:ml-auto w-full xl:w-auto xl:self-start">
          {characteristicsToDisplay.map((char, index) => (
            <div key={index} className="characteristic-card">
              {char}
            </div>
          ))}
        </div>
      </div>

      {showDescriptionSection && (
        <div className="flex flex-col mt-16 gap-6 w-full max-w-[1000px]">
          <h3 className="desc-heading text-[#FFFBE4]">
            Description
          </h3>
          <div className="description-text text-[#FFFBE4]">
            {renderDescription()}
          </div>
        </div>
      )}
    </div>
  );
};
