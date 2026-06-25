package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.ability.AbilityScoreDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbilityScoreService {
    AbilityScoreDto getById(String index);

    Page<AbilityScoreDto> getAll(Pageable pageable);
}
