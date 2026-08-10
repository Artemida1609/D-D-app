package mate.academy.jvteamproject.dto.classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;
    private String nameUa;
    @JsonProperty("hit_die")
    private Integer hitDie;
    @JsonProperty("proficiency_choices")
    private List<Map<String, Object>> proficiencyChoices;
    @JsonProperty("proficiencies")
    private List<Map<String, Object>> proficiencies;
    @JsonProperty("saving_throws")
    private List<Map<String, Object>> savingThrows;
    @JsonProperty("starting_equipment")
    private List<Map<String, Object>> startingEquipment;
    @JsonProperty("class_levels")
    private String classLevels;
    @JsonProperty("multi_classing")
    private Map<String, Object> multiClassing;
    private List<Map<String, Object>> subclasses;
    private String url;
}
