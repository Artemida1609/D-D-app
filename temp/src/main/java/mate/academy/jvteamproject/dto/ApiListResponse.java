package mate.academy.jvteamproject.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiListResponse {
    private int count;
    private List<ApiReferenceDto> results;
}
