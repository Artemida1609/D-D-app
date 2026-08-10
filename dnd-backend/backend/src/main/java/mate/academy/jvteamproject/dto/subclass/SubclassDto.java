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
    private String nameUa;

    @JsonProperty("subclass_flavor")
    private String subclassFlavor;
    private String subclassFlavorUa;

    @JsonProperty("class")
    private Map<String, Object> classRef;

    @JsonProperty("desc")
    private List<String> description;
    private List<String> descriptionUa;

    @JsonProperty("subclass_levels")
    private String subclassLevels;

    private String url;
}
