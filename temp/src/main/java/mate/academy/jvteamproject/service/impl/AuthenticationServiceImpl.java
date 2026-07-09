package mate.academy.jvteamproject.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.user.RefreshRequestDto;
import mate.academy.jvteamproject.dto.user.RefreshResponseDto;
import mate.academy.jvteamproject.dto.user.UserLoginRequestDto;
import mate.academy.jvteamproject.dto.user.UserLoginResponseDto;
import mate.academy.jvteamproject.model.RefreshToken;
import mate.academy.jvteamproject.model.User;
import mate.academy.jvteamproject.repository.UserRepository;
import mate.academy.jvteamproject.security.JwtUtil;
import mate.academy.jvteamproject.service.AuthenticationService;
import mate.academy.jvteamproject.service.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword()));

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtUtil.generateToken(authentication.getName());
        RefreshToken refreshToken = refreshTokenService.create(user.getId());

        return new UserLoginResponseDto(accessToken, refreshToken.getToken());
    }

    @Override
    public RefreshResponseDto refresh(RefreshRequestDto request) {

        RefreshToken rt = refreshTokenService.validate(request.getRefreshToken());

        User user = userRepository.findById(rt.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newAccessToken = jwtUtil.generateToken(user.getEmail());

        return new RefreshResponseDto(newAccessToken);
    }
}
