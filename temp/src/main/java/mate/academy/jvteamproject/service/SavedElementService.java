package mate.academy.jvteamproject.service;

import java.util.List;
import mate.academy.jvteamproject.dto.saved.SavedElementRequestDto;
import mate.academy.jvteamproject.dto.saved.SavedElementResponseDto;

public interface SavedElementService {
    SavedElementResponseDto saveElement(SavedElementRequestDto requestDto);

    void removeElement(Long entityId);

    List<SavedElementResponseDto> getAllSavedElementsByUser();
}
