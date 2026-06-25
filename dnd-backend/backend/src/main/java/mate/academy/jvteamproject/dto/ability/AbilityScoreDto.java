package mate.academy.jvteamproject.dto.ability;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbilityScoreDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("desc")
    private List<String> description;
    private List<Map<String, Object>> skills;
    private String url;
}
