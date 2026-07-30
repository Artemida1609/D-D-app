package mate.academy.jvteamproject.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import mate.academy.jvteamproject.dto.saved.SavedElementRequestDto;
import mate.academy.jvteamproject.dto.saved.SavedElementResponseDto;
import mate.academy.jvteamproject.mapper.SavedElementMapper;
import mate.academy.jvteamproject.model.SavedElement;
import mate.academy.jvteamproject.model.User;
import mate.academy.jvteamproject.repository.SavedElementRepository;
import mate.academy.jvteamproject.repository.UserRepository;
import mate.academy.jvteamproject.service.impl.SavedElementServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static mate.academy.jvteamproject.helper.TestDataHelper.*;
import static mate.academy.jvteamproject.helper.TestSecurityUtils.mockAuth;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SavedElementServiceTest {
    @InjectMocks
    private SavedElementServiceImpl service;
    @Mock
    private SavedElementRepository repository;
    @Mock
    private SavedElementMapper mapper;
    @Mock
    private UserRepository userRepository;

    @Test
    void saveElement_success() {
        mockAuth("email");
        User user = createUser("email", "password",
                "nickname");
        user.setId(1L);

        SavedElement savedElement =
                createSavedElement(1L, 1L, "Class", 1L, LocalDateTime.now());

        SavedElementRequestDto requestDto = createSavedElementRequestDto("Class", 1L);

        SavedElementResponseDto responseDto =
                createSavedElementResponseDto(1L,  "Class", 1L, LocalDateTime.now());

        when(userRepository.getUserByEmail("email")).thenReturn(user);
        when(mapper.toEntity(requestDto)).thenReturn(savedElement);
        when(repository.save(savedElement)).thenReturn(savedElement);
        when(mapper.toResponseDto(savedElement)).thenReturn(responseDto);

        SavedElementResponseDto actual = service.saveElement(requestDto);

        assertEquals(savedElement.getId(), actual.getId());
        assertEquals(savedElement.getEntityType(), actual.getEntityType());
        assertEquals(savedElement.getEntityId(), actual.getEntityId());
    }

    @Test
    void removeElement_success() {
        mockAuth("email");

        User user = createUser("email", "password",
                "nickname");
        user.setId(1L);

        SavedElement savedElement =
                createSavedElement(1L, 1L, "Class", 1L, LocalDateTime.now());

        Long userId = savedElement.getId();

        when(userRepository.getUserByEmail("email")).thenReturn(user);
        when(repository.findById(userId)).thenReturn(Optional.of(savedElement));
        doNothing().when(repository).deleteById(userId);
        service.removeElement(userId);

        verify(repository, times(1)).deleteById(userId);
    }

    @Test
    void getAllSavedElementsByUser_success() {
        mockAuth("email");
        User user = createUser("email", "password",
                "nickname");
        user.setId(1L);

        SavedElement savedElement =
                createSavedElement(1L, 1L, "Class", 1L, LocalDateTime.now());

        SavedElementResponseDto responseDto =
                createSavedElementResponseDto(1L,  "Class", 1L, LocalDateTime.now());

        when(userRepository.getUserByEmail("email")).thenReturn(user);
        when(repository.findByUserId(1L)).thenReturn(List.of(savedElement));
        when(mapper.toResponseDto(savedElement)).thenReturn(responseDto);

        List<SavedElementResponseDto> actual = service.getAllSavedElementsByUser();

        assertEquals(1, actual.size());
    }
}
