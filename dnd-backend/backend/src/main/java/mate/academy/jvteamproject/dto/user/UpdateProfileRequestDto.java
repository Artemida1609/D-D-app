package mate.academy.jvteamproject.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import mate.academy.jvteamproject.anotation.FieldMatch;

@Getter
@Setter
@FieldMatch(first = "password", second = "repeatPassword", message = "Passwords must match")
public class UpdateProfileRequestDto {
    @NotBlank
    private String userNickname;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;
}
