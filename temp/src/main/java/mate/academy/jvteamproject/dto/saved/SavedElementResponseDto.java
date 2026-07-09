package mate.academy.jvteamproject.dto.saved;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavedElementResponseDto {
    private Long id;
    private String entityType;
    private Long entityId;
    private LocalDateTime savedAt;
}
