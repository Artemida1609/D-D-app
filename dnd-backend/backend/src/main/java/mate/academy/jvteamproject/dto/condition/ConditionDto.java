package mate.academy.jvteamproject.dto.condition;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConditionDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;
    @JsonProperty("desc")
    private List<String> description;
    private String url;
}
