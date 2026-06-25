package mate.academy.jvteamproject.dto.race;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RaceDto {

    @JsonProperty("index")
    private String originalIndex;
    private String name;
    private Integer speed;

    @JsonProperty("ability_bonuses")
    private List<Map<String, Object>> abilityBonuses;

    @JsonProperty("alignment")
    private String alignment;

    @JsonProperty("age")
    private String age;

    private String size;

    @JsonProperty("size_description")
    private String sizeDescription;

    @JsonProperty("starting_proficiencies")
    private List<Map<String, Object>> startingProficiencies;

    @JsonProperty("languages")
    private List<Map<String, Object>> languages;

    @JsonProperty("language_desc")
    private String languageDesc;

    @JsonProperty("traits")
    private List<Map<String, Object>> traits;

    @JsonProperty("subraces")
    private List<Map<String, Object>> subraces;

    private String url;
}
