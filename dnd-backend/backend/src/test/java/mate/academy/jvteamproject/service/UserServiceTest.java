package mate.academy.jvteamproject.service;

import java.util.Optional;
import mate.academy.jvteamproject.dto.user.UserRegistrationRequestDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationResponseDto;
import mate.academy.jvteamproject.mapper.UserMapper;
import mate.academy.jvteamproject.model.Role;
import mate.academy.jvteamproject.model.User;
import mate.academy.jvteamproject.repository.RoleRepository;
import mate.academy.jvteamproject.repository.UserRepository;
import mate.academy.jvteamproject.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static mate.academy.jvteamproject.helper.TestDataHelper.createRole;
import static mate.academy.jvteamproject.helper.TestDataHelper.createUser;
import static mate.academy.jvteamproject.helper.TestDataHelper.createUserRegistrationRequestDto;
import static mate.academy.jvteamproject.helper.TestDataHelper.createUserRegistrationResponseDto;
import static mate.academy.jvteamproject.helper.TestSecurityUtils.mockAuth;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;

//    @Test
//    void registration_success() {
//        UserRegistrationRequestDto requestDto = createUserRegistrationRequestDto("email", "password",
//                "password", "nickname");
//
//        Role role = createRole(1L,  Role.RoleName.USER);
//
//        User user = createUser("email", "password",
//                "nickname");
//
//        UserRegistrationResponseDto expected = createUserRegistrationResponseDto(user.getId(), user.getEmail(),
//                user.getUserNickname());
//
//        when(repository.findByEmail("email")).thenReturn(Optional.empty());
//        when(mapper.toEntity(requestDto)).thenReturn(user);
//        when(passwordEncoder.encode(requestDto.getPassword())).thenReturn("encodedPassword");
//        when(roleRepository.findRoleByName(Role.RoleName.USER)).thenReturn(role);
//        when(repository.save(user)).thenReturn(user);
//        when(mapper.toRegistrationDto(user)).thenReturn(expected);
//
//        UserRegistrationResponseDto actual = service.register(requestDto);
//
//        assertEquals(expected.getId(), actual.getId());
//        assertEquals(expected.getEmail(), actual.getEmail());
//    }

    @Test
    void delete_success() {
        mockAuth("email");
        User user = createUser("email", "password",
                "nickname");
        user.setId(6L);
        when(repository.getUserByEmail("email")).thenReturn(user);
        doNothing().when(repository).deleteById(user.getId());
        service.deleteUser();

        verify(repository, times(1)).deleteById(user.getId());
    }
}
