package mate.academy.jvteamproject.dto.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.jvteamproject.deserialize.StringOrListDeserializer;

@Getter
@Setter
public class MagicSchoolDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;
    @JsonProperty("desc")
    @JsonDeserialize(using = StringOrListDeserializer.class)
    private List<String> description;
    private String url;
}
