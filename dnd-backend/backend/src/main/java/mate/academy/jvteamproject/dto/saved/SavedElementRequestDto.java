package mate.academy.jvteamproject.dto.saved;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavedElementRequestDto {
    @NotBlank
    private String entityType;
    @NotNull
    private Long entityId;
}
