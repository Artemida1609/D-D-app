package mate.academy.jvteamproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchResult {
    private String entityType;
    private String name;
    private String nameUa;
    private String originalIndex;
    private String url;
    private Object data;
}
