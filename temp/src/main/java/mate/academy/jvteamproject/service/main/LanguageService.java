package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.language.LanguageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LanguageService {
    LanguageDto getByOriginalIndex(String index);

    Page<LanguageDto> getAll(Pageable pageable);
}
