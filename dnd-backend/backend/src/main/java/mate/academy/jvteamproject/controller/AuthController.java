package mate.academy.jvteamproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.user.RefreshRequestDto;
import mate.academy.jvteamproject.dto.user.RefreshResponseDto;
import mate.academy.jvteamproject.dto.user.UserLoginRequestDto;
import mate.academy.jvteamproject.dto.user.UserLoginResponseDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationResponseDto;
import mate.academy.jvteamproject.service.AuthenticationService;
import mate.academy.jvteamproject.service.RefreshTokenService;
import mate.academy.jvteamproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "auth", description = "registration and authentication of users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    @Operation(summary = "login", description = "log in existing account")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.login(requestDto);
    }

    @PostMapping(value = "/registration", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "registration", description = "register new user")
    public UserRegistrationResponseDto register(
            @RequestPart("data") String data,
            @RequestPart(value = "avatar", required = false) MultipartFile avatar
    ) {

        return userService.register(data, avatar);
    }

    @PostMapping("/refresh")
    public RefreshResponseDto refresh(@RequestBody RefreshRequestDto request) {
        return authenticationService.refresh(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestBody RefreshRequestDto request) {
        refreshTokenService.revoke(request.getRefreshToken());
    }
}
