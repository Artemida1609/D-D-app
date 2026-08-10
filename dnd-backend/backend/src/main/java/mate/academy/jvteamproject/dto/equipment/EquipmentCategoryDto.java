package mate.academy.jvteamproject.dto.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentCategoryDto {
    @JsonProperty("index")
    private String originalIndex;
    private String globalCategory;
    private String name;
    private String nameUa;
    private List<Map<String, Object>> equipment;
    private String url;
}
