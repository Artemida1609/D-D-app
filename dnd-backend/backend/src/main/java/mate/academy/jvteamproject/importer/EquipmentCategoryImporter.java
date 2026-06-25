package mate.academy.jvteamproject.importer;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mate.academy.jvteamproject.dto.equipment.EquipmentCategoryDto;
import mate.academy.jvteamproject.importer.client.ApiClient;
import mate.academy.jvteamproject.mapper.main.EquipmentCategoryMapper;
import mate.academy.jvteamproject.model.main.EquipmentCategory;
import mate.academy.jvteamproject.repository.main.EquipmentCategoryRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EquipmentCategoryImporter {

    private static final Map<String, String> CATEGORY_MAP = Map.ofEntries(
            // ARMOR
            Map.entry("armor", "armor"),
            Map.entry("heavy-armor", "armor"),
            Map.entry("medium-armor", "armor"),
            Map.entry("light-armor", "armor"),
            Map.entry("shields", "armor"),

            // WEAPONS
            Map.entry("weapon", "weapon"),
            Map.entry("melee-weapons", "weapon"),
            Map.entry("ranged-weapons", "weapon"),
            Map.entry("martial-weapons", "weapon"),
            Map.entry("martial-melee-weapons", "weapon"),
            Map.entry("martial-ranged-weapons", "weapon"),
            Map.entry("simple-weapons", "weapon"),
            Map.entry("simple-melee-weapons", "weapon"),
            Map.entry("simple-ranged-weapons", "weapon"),

            // EVERYTHING ELSE → gear
            Map.entry("adventuring-gear", "gear"),
            Map.entry("equipment-packs", "gear"),
            Map.entry("tools", "gear"),
            Map.entry("kits", "gear"),
            Map.entry("scroll", "gear"),
            Map.entry("potion", "gear"),
            Map.entry("ring", "gear"),
            Map.entry("wand", "gear"),
            Map.entry("rod", "gear"),
            Map.entry("staff", "gear"),
            Map.entry("wondrous-items", "gear"),
            Map.entry("holy-symbols", "gear"),
            Map.entry("arcane-foci", "gear"),
            Map.entry("druidic-foci", "gear"),
            Map.entry("gaming-sets", "gear"),
            Map.entry("musical-instruments", "gear"),
            Map.entry("standard-gear", "gear"),
            Map.entry("other-tools", "gear"),
            Map.entry("mounts-and-vehicles", "gear"),
            Map.entry("land-vehicles", "gear"),
            Map.entry("waterborne-vehicles", "gear"),
            Map.entry("mounts-and-other-animals", "gear")
    );

    private final EquipmentCategoryRepository categoryRepository;
    private final EquipmentCategoryMapper equipmentCategoryMapper;
    private final UniversalReferenceFixer universalReferenceFixer;
    private final ApiClient apiClient;

    public void importCategories() {
        System.out.println("Importing: /api/equipment-categories");
        try {
            Map<String, Object> response =
                    apiClient.get("/api/equipment-categories", Map.class);

            List<Map<String, Object>> results =
                    (List<Map<String, Object>>) response.get("results");

            for (Map<String, Object> item : results) {
                EquipmentCategoryDto dto = apiClient.get((String )item
                        .get("url"), EquipmentCategoryDto.class);
                String global = CATEGORY_MAP.getOrDefault(dto.getOriginalIndex(), "gear");
                universalReferenceFixer.fix(dto);
                EquipmentCategory category = equipmentCategoryMapper.toEntity(dto);
                category.setGlobalCategory(global);

                if (categoryRepository.existsByOriginalIndex(category.getOriginalIndex())) {
                    continue;
                }
                categoryRepository.save(category);
            }

        } catch (Exception e) {
            log.error("Failed to import equipment categories", e);
            throw new RuntimeException("Import failed", e);
        }
    }
}
