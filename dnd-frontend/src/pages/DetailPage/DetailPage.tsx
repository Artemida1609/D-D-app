import { useEffect, useState } from "react";
import { PageTitle } from "../../shared/ui/PageTitle";
import { Button } from "../../shared/ui/Button/Button";
import { useLocation } from "react-router-dom";
import { useFavoritesStore } from "../../shared/store/favoritesStore";
import "./DetailPage.scss";

const API_BASE_URL = import.meta.env.VITE_API_URL || "http://16.171.136.146";

const propertyFormatters: Record<string, (val: any) => string | null> = {
  cost: (val) => (val?.quantity !== undefined ? `Cost: ${val.quantity} ${val.unit}` : null),
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
  const [data, setData] = useState<any>(null);
  const [isLoading, setIsLoading] = useState(true);

  const pathParts = location.pathname.split("/").filter(Boolean);
  const mainCategory = pathParts[0] || "character";
  const subCategory = (pathParts[1] || "category").toLowerCase();
  const itemId = pathParts[2] || "";
  const currentId = location.pathname;

  useEffect(() => {
    const fetchData = async () => {
      if (!itemId) {
        setIsLoading(false);
        return;
      }

      try {
        setIsLoading(true);
        
        let apiCategory = subCategory;
        
        if (subCategory === "class") {
          apiCategory = "classes";
        } else if (subCategory === "species" || subCategory === "race") {
          apiCategory = "races";
        } else if (["armors", "weapons", "gear", "adventuring-gear"].includes(subCategory)) {
          apiCategory = "equipments";
        } else if (subCategory === "magic-items") {
          apiCategory = "magic-items";
        } else if (subCategory === "schools" || subCategory === "school") {
          apiCategory = "magic-schools"; // ИСПРАВЛЕНИЕ ДЛЯ ШКОЛ МАГИИ
        } else if (!subCategory.endsWith("s") && subCategory !== "equipments") {
          apiCategory = `${subCategory}s`;
        }

        const fetchUrl = `${API_BASE_URL}/api/${apiCategory}/${itemId}`;
        
        const response = await fetch(fetchUrl);
        
        if (!response.ok) {
          throw new Error("Failed to fetch detail data");
        }

        const result = await response.json();
        setData(result);
      } catch (error) {
        console.error("Fetch error:", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [location.pathname, subCategory, itemId]);

  const displayTitle = data?.name || data?.title || subCategory.charAt(0).toUpperCase() + subCategory.slice(1);
  
  let fetchedImage = data?.image || data?.imageUrl || data?.icon || "";
  if (fetchedImage.startsWith("/api/images/")) {
    fetchedImage = `https://www.dnd5eapi.co${fetchedImage}`;
  }
  const imagePath = fetchedImage || `/images/icons/placeholders/${mainCategory.toLowerCase()}.png`;

  const toggleFavorite = useFavoritesStore((state) => state.toggleFavorite);
  const isFavorite = useFavoritesStore((state) => state.isFavorite(currentId));

  const handleFavoriteClick = () => {
    toggleFavorite({
      id: currentId,
      title: displayTitle,
      category: mainCategory.charAt(0).toUpperCase() + mainCategory.slice(1),
      path: location.pathname,
      icon: fetchedImage,
    });
  };

  const dynamicCharacteristics: string[] = [];
  if (data) {
    const ignoreKeys = ['_id', 'index', 'url', 'name', 'title', 'desc', 'description', 'image', 'imageUrl', 'icon', 'special_abilities', 'actions', 'legendary_actions', 'equipment_category', 'weapon_category', 'weapon_range', 'armor_category', 'gear_category', 'age', 'alignment', 'size_description', 'language_desc', 'traits', 'starting_proficiencies'];
    
    for (const [key, value] of Object.entries(data)) {
      if (ignoreKeys.includes(key) || value == null) continue;
      
      const formattedKey = key.replace(/_/g, ' ').replace(/\b\w/g, l => l.toUpperCase());

      if (typeof value === 'string' || typeof value === 'number') {
        if (key === 'weight') {
          dynamicCharacteristics.push(`${formattedKey}: ${value} lb.`);
        } else {
          dynamicCharacteristics.push(`${formattedKey}: ${value}`);
        }
      } else if (typeof value === 'object' && !Array.isArray(value) || key === 'properties') {
        const formatter = propertyFormatters[key];
        if (formatter) {
          const formattedString = formatter(value);
          if (formattedString) {
            dynamicCharacteristics.push(formattedString);
          }
        }
      }
    }
  }

  const characteristicsToDisplay = dynamicCharacteristics.length > 0 
    ? dynamicCharacteristics.slice(0, 8) 
    : ["No additional characteristics"];

  const renderDescription = () => {
    if (!data) return null;
    
    const raceDetails = [];
    if (data.alignment) raceDetails.push({ title: "Alignment", text: data.alignment });
    if (data.age) raceDetails.push({ title: "Age", text: data.age });
    if (data.size_description) raceDetails.push({ title: "Size", text: data.size_description });
    if (data.language_desc) raceDetails.push({ title: "Languages", text: data.language_desc });

    if (raceDetails.length > 0) {
      return (
        <div className="flex flex-col gap-6">
          {raceDetails.map((detail, idx) => (
            <div key={`race-detail-${idx}`}>
              <h4 className="text-[#FFFBE4] text-[20px] font-bold mb-3 font-manrope">{detail.title}</h4>
              <p className="opacity-90">{detail.text}</p>
            </div>
          ))}
        </div>
      );
    }

    const descData = data.desc || data.description;
    
    if (Array.isArray(descData) && descData.length > 0) {
      return descData.map((paragraph: string, index: number) => (
        <p key={`desc-${index}`} className="mb-6">{paragraph}</p>
      ));
    }
    
    if (typeof descData === 'string' && descData.trim() !== '') {
      return <p>{descData}</p>;
    }
    
    const hasAbilities = data.special_abilities && data.special_abilities.length > 0;
    const hasActions = data.actions && data.actions.length > 0;

    if (hasAbilities || hasActions) {
      return (
        <div className="flex flex-col gap-6">
          {hasAbilities && (
            <div>
              <h4 className="text-[#FFFBE4] text-[20px] font-bold mb-3">Special Abilities</h4>
              {data.special_abilities.map((ability: any, idx: number) => (
                <p key={`ability-${idx}`} className="mb-3">
                  <strong className="text-[#FFFBE4] opacity-100">{ability.name}: </strong>
                  <span className="opacity-80">{ability.desc}</span>
                </p>
              ))}
            </div>
          )}
          
          {hasActions && (
            <div className="mt-4">
              <h4 className="text-[#FFFBE4] text-[20px] font-bold mb-3">Actions</h4>
              {data.actions.map((action: any, idx: number) => (
                <p key={`action-${idx}`} className="mb-3">
                  <strong className="text-[#FFFBE4] opacity-100">{action.name}: </strong>
                  <span className="opacity-80">{action.desc}</span>
                </p>
              ))}
            </div>
          )}
        </div>
      );
    }
    
    if (data.equipment_category) {
      const catName = data.equipment_category.name?.toLowerCase() || data.equipment_category.index;
      const itemName = data.name || data.title;

      if (catName === 'weapon') {
        const wCat = data.weapon_category?.toLowerCase() || '';
        const wRange = data.weapon_range?.toLowerCase() || '';
        return <p>The {itemName} is a {wCat} {wRange} weapon.</p>;
      }

      if (catName === 'armor') {
        const aCat = data.armor_category?.toLowerCase() || '';
        return <p>The {itemName} is a type of {aCat} armor.</p>;
      }

      const gCat = data.gear_category?.name || '';
      if (gCat) {
        return <p>The {itemName} is a piece of adventuring gear belonging to the {gCat} category.</p>;
      }

      return <p>The {itemName} is a piece of {catName} equipment.</p>;
    }
    
    return <p>No detailed description available for this item.</p>;
  };

  if (isLoading) {
    return (
      <div className="flex-1 w-full flex justify-center items-center text-[#FFFBE4] min-h-[50vh]">
        Loading...
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
              className="w-full h-full object-cover"
              onError={(e) => {
                e.currentTarget.src = `/images/icons/placeholders/${mainCategory.toLowerCase()}.png`;
              }}
            />
          ) : (
            <img
              src={imagePath}
              alt={displayTitle}
              className="w-[160px] h-[160px] object-contain"
            />
          )}
        </div>

        <div className="flex flex-col gap-8 flex-shrink-0 xl:w-[300px]">
          <h2 className="text-[#FFFBE4] text-[40px] md:text-[48px] font-medium leading-none font-manrope">
            {displayTitle}
          </h2>
          
          <Button 
            className={`w-full md:w-[380px] flex items-center justify-center gap-2 transition-colors ${
              isFavorite ? "!bg-[#EF4444] !border-[#EF4444]" : ""
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

        <div className="flex flex-col gap-4 xl:ml-10 w-full xl:w-auto">
          {characteristicsToDisplay.map((char, index) => (
            <div key={index} className="characteristic-card">
              {char}
            </div>
          ))}
        </div>
      </div>

      <div className="flex flex-col mt-16 gap-6 w-full max-w-[1000px]">
        <h3 className="text-[#FFFBE4] text-[32px] font-medium font-manrope">
          Description
        </h3>
        <div className="description-text text-[#FFFBE4]">
          {renderDescription()}
        </div>
      </div>
    </div>
  );
};
