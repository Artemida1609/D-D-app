package mate.academy.jvteamproject.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.jvteamproject.deserialize.StringOrListDeserializer;

@Getter
@Setter
public class RuleDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;
    private String nameUa;

    @JsonProperty("desc")
    @JsonDeserialize(using = StringOrListDeserializer.class)
    private List<String> description;
    private List<String> descriptionUa;

    private String url;
}
