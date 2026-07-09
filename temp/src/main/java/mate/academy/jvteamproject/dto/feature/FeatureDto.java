package mate.academy.jvteamproject.dto.feature;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatureDto {
    @JsonProperty("index")
    private String originalIndex;

    private String name;
    private Integer level;

    @JsonProperty("class")
    private Map<String, Object> classRef;

    @JsonProperty("subclass")
    private Map<String, Object> subclassRef;

    private List<Map<String, Object>> prerequisites;

    @JsonProperty("desc")
    private List<String> description;

    @JsonProperty("feature_specific")
    private Map<String, Object> featureSpecific;

    private String url;
}
