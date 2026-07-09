package mate.academy.jvteamproject.dto.subclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubclassDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;

    @JsonProperty("subclass_flavor")
    private String subclassFlavor;

    @JsonProperty("class")
    private Map<String, Object> classRef;

    @JsonProperty("desc")
    private List<String> description;

    @JsonProperty("subclass_levels")
    private String subclassLevels;

    private String url;
}
