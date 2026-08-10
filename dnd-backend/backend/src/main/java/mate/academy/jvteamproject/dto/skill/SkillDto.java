package mate.academy.jvteamproject.dto.skill;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;
    private String nameUa;

    @JsonProperty("desc")
    private List<String> description;
    private List<String> descriptionUa;

    @JsonProperty("ability_score")
    private Map<String, Object> abilityScore;

    private String url;
}
