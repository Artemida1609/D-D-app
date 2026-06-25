package mate.academy.jvteamproject.dto.language;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.jvteamproject.deserialize.StringOrListDeserializer;

@Getter
@Setter
public class LanguageDto {
    @JsonProperty("index")
    private String originalIndex;
    private String name;
    private String type;
    private String script;

    @JsonProperty("desc")
    @JsonDeserialize(using = StringOrListDeserializer.class)
    private List<String> description;

    @JsonProperty("typical_speakers")
    @JsonDeserialize(using = StringOrListDeserializer.class)
    private List<String> typicalSpeakers;

    private String url;
}
