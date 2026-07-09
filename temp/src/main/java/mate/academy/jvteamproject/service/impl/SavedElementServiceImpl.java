package mate.academy.jvteamproject.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.saved.SavedElementRequestDto;
import mate.academy.jvteamproject.dto.saved.SavedElementResponseDto;
import mate.academy.jvteamproject.mapper.SavedElementMapper;
import mate.academy.jvteamproject.model.SavedElement;
import mate.academy.jvteamproject.model.User;
import mate.academy.jvteamproject.repository.SavedElementRepository;
import mate.academy.jvteamproject.repository.UserRepository;
import mate.academy.jvteamproject.service.SavedElementService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SavedElementServiceImpl implements SavedElementService {

    private final SavedElementRepository savedElementRepository;
    private final SavedElementMapper savedElementMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public SavedElementResponseDto saveElement(SavedElementRequestDto requestDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByEmail(username);

        SavedElement element = savedElementMapper.toEntity(requestDto);
        element.setUserId(user.getId());
        element.setSavedAt(LocalDateTime.now());
        element.setEntityId(requestDto.getEntityId());
        element.setEntityType(requestDto.getEntityType());

        return savedElementMapper.toResponseDto(savedElementRepository.save(element));
    }

    @Override
    @Transactional
    public void removeElement(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByEmail(username);

        SavedElement savedElement = savedElementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Element not found"));

        if (savedElement.getUserId().equals(user.getId())) {
            savedElementRepository.deleteById(id);
        } else {
            throw new RuntimeException("User cannot delete someone else's Element");
        }
    }

    @Override
    @Transactional
    public List<SavedElementResponseDto> getAllSavedElementsByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByEmail(username);

        return savedElementRepository.findByUserId(user.getId()).stream()
                .map(savedElementMapper::toResponseDto).collect(Collectors.toList());
    }
}
