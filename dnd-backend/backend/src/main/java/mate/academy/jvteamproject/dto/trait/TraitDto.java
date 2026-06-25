package mate.academy.jvteamproject.dto.trait;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TraitDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;

    @JsonProperty("desc")
    private List<String> description;

    @JsonProperty("races")
    private List<Map<String, Object>> races;

    @JsonProperty("subraces")
    private List<Map<String, Object>> subraces;

    @JsonProperty("proficiencies")
    private List<Map<String, Object>> proficiencies;

    @JsonProperty("trait_specific")
    private Map<String, Object> traitSpecific;

    private String url;
}
