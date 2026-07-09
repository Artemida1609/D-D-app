package mate.academy.jvteamproject.dto.monster;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterDto {

    @JsonProperty("index")
    private String originalIndex;
    private String name;
    private String size;
    private String type;
    private String alignment;

    @JsonProperty("armor_class")
    private List<Map<String, Object>> armorClassData;

    @JsonProperty("hit_points")
    private Integer hitPoints;

    @JsonProperty("hit_dice")
    private String hitDice;

    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
    private Integer intelligence;
    private Integer wisdom;
    private Integer charisma;

    @JsonProperty("speed")
    private Map<String, Object> speed;

    private List<Map<String, Object>> proficiencies;

    @JsonProperty("senses")
    private Map<String, Object> senses;

    private String languages;

    @JsonProperty("challenge_rating")
    private Double challengeRating;

    private Integer xp;

    private List<Map<String, Object>> actions;

    @JsonProperty("special_abilities")
    private List<Map<String, Object>> specialAbilities;

    @JsonProperty("legendary_actions")
    private List<Map<String, Object>> legendaryActions;

    private List<Map<String, Object>> reactions;

    private String image;

    private String url;
}
