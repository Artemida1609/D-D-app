package mate.academy.jvteamproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.user.UpdateProfileRequestDto;
import mate.academy.jvteamproject.dto.user.UserDto;
import mate.academy.jvteamproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "user", description = "users controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/profile-update")
    @Operation(summary = "update profile info", description = "update user's profile info")
    public UserDto updateProfileInfo(@RequestBody @Valid UpdateProfileRequestDto requestDto) {
        return userService.updateProfileInfo(requestDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/avatar-get")
    @Operation(summary = "get avatar", description = "get user's avatar")
    public ResponseEntity<byte[]> getAvatar() {
        return userService.getAvatar();
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/avatar-update")
    @Operation(summary = "update avatar", description = "update user's avatar")
    public ResponseEntity<?> updateAvatar(@RequestParam MultipartFile file) {
        return userService.updateAvatar(file);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/avatar-delete")
    @Operation(summary = "delete avatar", description = "delete user's avatar")
    public void deleteAvatar() {
        userService.deleteAvatar();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete user", description = "delete a user")
    public void deleteUser() {
        userService.deleteUser();
    }
}
