package mate.academy.jvteamproject.service;

import mate.academy.jvteamproject.dto.user.RefreshRequestDto;
import mate.academy.jvteamproject.dto.user.RefreshResponseDto;
import mate.academy.jvteamproject.dto.user.UserLoginRequestDto;
import mate.academy.jvteamproject.dto.user.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto login(UserLoginRequestDto request);

    RefreshResponseDto refresh(RefreshRequestDto request);
}
