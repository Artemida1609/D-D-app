package mate.academy.jvteamproject.service.impl;

import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.user.UserRegistrationRequestDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationResponseDto;
import mate.academy.jvteamproject.exception.RegistrationException;
import mate.academy.jvteamproject.mapper.UserMapper;
import mate.academy.jvteamproject.model.Role;
import mate.academy.jvteamproject.model.User;
import mate.academy.jvteamproject.repository.RoleRepository;
import mate.academy.jvteamproject.repository.UserRepository;
import mate.academy.jvteamproject.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto) {
        Optional<User> user = userRepository.findByEmail(requestDto.getEmail());

        if (user.isEmpty()) {
            User newUser = userMapper.toEntity(requestDto);
            newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            Role defaultRole = roleRepository.findRoleByName(Role.RoleName.USER);
            newUser.setRoles(Set.of(defaultRole));
            userRepository.save(newUser);

            return userMapper.toRegistrationDto(newUser);
        }
        throw new RegistrationException("User already exists");
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
