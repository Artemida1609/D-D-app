package mate.academy.jvteamproject.dto.magic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MagicItemDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;
    @JsonProperty("desc")
    private List<String> description;
    @JsonProperty("equipment_category")
    private Map<String, Object> equipmentCategory;
    private Map<String, Object> rarity;
    private List<Map<String, Object>> variants;
    private String image;
    private String url;
}
