package mate.academy.jvteamproject.dto.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentDto {
    @JsonProperty("index")
    private String originalIndex;

    @JsonProperty("name")
    private String name;
    private String nameUa;

    @JsonProperty("desc")
    private List<String> desc;
    private List<String> descUa;

    @JsonProperty("equipment_category")
    private Map<String, Object> equipmentCategory;

    @JsonProperty("gear_category")
    private Map<String, Object> gearCategory;

    @JsonProperty("cost")
    private Map<String, Object> cost;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("armor_category")
    private String armorCategory;

    @JsonProperty("armor_class")
    private Map<String, Object> armorClass;

    @JsonProperty("capacity")
    private String capacity;

    @JsonProperty("category_range")
    private String categoryRange;

    @JsonProperty("contents")
    private List<Map<String, Object>> contents;

    @JsonProperty("damage")
    private Map<String, Object> damage;

    @JsonProperty("image")
    private String image;

    @JsonProperty("properties")
    private List<Map<String, Object>> properties;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("range")
    private Map<String, Object> range;

    @JsonProperty("special")
    private List<String> special;

    @JsonProperty("speed")
    private Map<String, Object> speed;

    @JsonProperty("stealth_disadvantage")
    private Boolean stealthDisadvantage;

    @JsonProperty("str_minimum")
    private Integer strMinimum;

    @JsonProperty("throw_range")
    private Map<String, Object> throwRange;

    @JsonProperty("tool_category")
    private String toolCategory;

    @JsonProperty("two_handed_damage")
    private Map<String, Object> twoHandedDamage;

    @JsonProperty("vehicle_category")
    private String vehicleCategory;

    @JsonProperty("weapon_category")
    private String weaponCategory;

    @JsonProperty("weapon_range")
    private String weaponRange;

    private String url;
}
