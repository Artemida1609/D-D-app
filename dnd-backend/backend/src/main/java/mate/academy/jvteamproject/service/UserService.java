package mate.academy.jvteamproject.service;

import mate.academy.jvteamproject.dto.user.UpdateProfileRequestDto;
import mate.academy.jvteamproject.dto.user.UserDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserRegistrationResponseDto register(String data, MultipartFile avatar);

    UserDto updateProfileInfo(UpdateProfileRequestDto requestDto);

    ResponseEntity<?> updateAvatar(MultipartFile file);

    ResponseEntity<byte[]> getAvatar();

    void deleteAvatar();

    void deleteUser();
}
