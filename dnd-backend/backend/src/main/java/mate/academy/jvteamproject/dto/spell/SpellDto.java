package mate.academy.jvteamproject.dto.spell;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpellDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;
    private String nameUa;
    private Integer level;

    @JsonProperty("school")
    private Map<String, Object> school;

    @JsonProperty("classes")
    private List<Map<String, Object>> classes;

    @JsonProperty("subclasses")
    private List<Map<String, Object>> subclasses;

    @JsonProperty("desc")
    private List<String> description;
    private List<String> descriptionUa;

    @JsonProperty("higher_level")
    private List<String> higherLevel;
    private List<String> higherLevelUa;

    private String range;

    private List<String> components;

    private String material;

    private Boolean ritual;
    private String duration;
    private Boolean concentration;

    @JsonProperty("casting_time")
    private String castingTime;

    @JsonProperty("attack_type")
    private String attackType;

    @JsonProperty("damage")
    private Map<String, Object> damage;

    @JsonProperty("dc")
    private Map<String, Object> dc;

    @JsonProperty("area_of_effect")
    private Map<String, Object> areaOfEffect;

    private String url;
}
