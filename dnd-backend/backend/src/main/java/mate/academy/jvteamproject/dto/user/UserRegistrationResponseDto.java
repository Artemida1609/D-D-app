package mate.academy.jvteamproject.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationResponseDto {
    private Long id;
    private String email;
    private String userNickname;
}
