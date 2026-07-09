package mate.academy.jvteamproject.dto.subrace;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import mate.academy.jvteamproject.deserialize.StringOrListDeserializer;

@Getter
@Setter
public class SubraceDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;

    @JsonProperty("race")
    private Map<String, Object> race;

    @JsonProperty("desc")
    @JsonDeserialize(using = StringOrListDeserializer.class)
    private List<String> description;

    @JsonProperty("ability_bonuses")
    private List<Map<String, Object>> abilityBonuses;

    @JsonProperty("racial_traits")
    private List<Map<String, Object>> racialTraits;

    private String url;
}
