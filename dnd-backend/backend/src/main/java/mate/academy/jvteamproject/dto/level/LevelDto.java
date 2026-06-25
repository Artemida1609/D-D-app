package mate.academy.jvteamproject.dto.level;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LevelDto {
    private String originalIndex;
    private Integer level;

    @JsonProperty("ability_score_bonuses")
    private Integer abilityScoreBonuses;

    @JsonProperty("prof_bonus")
    private Integer profBonus;

    @JsonProperty("features")
    private List<Map<String, Object>> features;

    @JsonProperty("spellcasting")
    private Map<String, Object> spellcasting;

    @JsonProperty("class_specific")
    private Map<String, Object> classSpecific;

    @JsonProperty("class")
    private Map<String, Object> classInfo;

    private String url;
}
