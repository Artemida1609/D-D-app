package mate.academy.jvteamproject.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.DropboxUploadResponse;
import mate.academy.jvteamproject.dto.user.UpdateProfileRequestDto;
import mate.academy.jvteamproject.dto.user.UserDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationRequestDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationResponseDto;
import mate.academy.jvteamproject.exception.RegistrationException;
import mate.academy.jvteamproject.mapper.UserMapper;
import mate.academy.jvteamproject.model.Avatar;
import mate.academy.jvteamproject.model.Role;
import mate.academy.jvteamproject.model.User;
import mate.academy.jvteamproject.repository.AvatarRepository;
import mate.academy.jvteamproject.repository.RoleRepository;
import mate.academy.jvteamproject.repository.UserRepository;
import mate.academy.jvteamproject.service.UserService;
import mate.academy.jvteamproject.service.dropbox.DropboxService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final DropboxService dropboxService;
    private final AvatarRepository avatarRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public UserRegistrationResponseDto register(String data,
                                                MultipartFile avatar) {
        UserRegistrationRequestDto requestDto;
        try {
            requestDto = objectMapper.readValue(data, UserRegistrationRequestDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Optional<User> existing = userRepository.findByEmail(requestDto.getEmail());
        if (existing.isPresent()) {
            throw new RegistrationException("User already exists");
        }

        User newUser = userMapper.toEntity(requestDto);
        newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        Role defaultRole = roleRepository.findRoleByName(Role.RoleName.USER);
        newUser.setRoles(new HashSet<>(Set.of(defaultRole)));

        newUser = userRepository.save(newUser);

        if (avatar != null && !avatar.isEmpty()) {

            String filename = newUser.getId() + ".png";
            DropboxUploadResponse response = dropboxService.upload(avatar, filename);

            Avatar newAvatar = new Avatar();
            newAvatar.setUser(newUser);
            newAvatar.setImageUrl(response.getImageUrl());
            newAvatar.setFileName(filename);
            newAvatar.setFileId(response.getFileId());

            avatarRepository.save(newAvatar);

            newUser.setAvatar(newAvatar);
            userRepository.save(newUser);
        }

        return userMapper.toRegistrationDto(newUser);
    }

    @Override
    public UserDto updateProfileInfo(UpdateProfileRequestDto requestDto) {
        User user = userRepository.getUserById(getUserId());
        userMapper.updateUserFromDto(requestDto, user);

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateAvatar(MultipartFile file) {
        User user = userRepository.getUserById(getUserId());

        Avatar avatar = user.getAvatar();
        DropboxUploadResponse response;

        if (avatar != null) {
            dropboxService.deleteFile("/" + avatar.getFileName());
            response = dropboxService.upload(file, avatar.getFileName());

            avatar.setImageUrl(response.getImageUrl());
            avatar.setFileId(response.getFileId());
            avatarRepository.save(avatar);

            return ResponseEntity.ok(response.getImageUrl());
        }

        String filename = user.getId() + ".png";
        response = dropboxService.upload(file, filename);

        Avatar newAvatar = new Avatar();
        newAvatar.setUser(user);
        newAvatar.setImageUrl(response.getImageUrl());
        newAvatar.setFileName(filename);
        newAvatar.setFileId(response.getFileId());

        avatarRepository.save(newAvatar);

        user.setAvatar(newAvatar);
        userRepository.save(user);

        return ResponseEntity.ok(response.getImageUrl());
    }

    @Override
    public ResponseEntity<byte[]> getAvatar() {
        User user = userRepository.getUserById(getUserId());
        Avatar avatar = user.getAvatar();

        if (avatar == null) {
            throw new RuntimeException("Avatar is null");
        }

        byte[] imageBytes = dropboxService.downloadFile(avatar.getImageUrl());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }

    @Override
    @Transactional
    public void deleteAvatar() {
        User user = userRepository.getUserById(getUserId());
        Avatar avatar = user.getAvatar();

        if (avatar == null) {
            throw new RegistrationException("Avatar is null");
        }

        String filename = user.getId() + ".png";

        dropboxService.deleteFile("/" + filename);

        user.setAvatar(null);
        userRepository.save(user);

        avatarRepository.deleteById(avatar.getId());
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private Long getUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.getUserByEmail(username).getId();
    }
}
