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

    @JsonProperty("desc")
    private List<String> description;

    @JsonProperty("ability_score")
    private Map<String, Object> abilityScore;

    private String url;
}
