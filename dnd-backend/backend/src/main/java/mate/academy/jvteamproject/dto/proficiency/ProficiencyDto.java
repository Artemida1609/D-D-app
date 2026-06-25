package mate.academy.jvteamproject.dto.proficiency;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProficiencyDto {

    @JsonProperty("index")
    private String originalIndex;
    private String name;
    private String type;
    private List<Map<String, Object>> classes;
    private List<Map<String, Object>> races;
    private String url;
}
