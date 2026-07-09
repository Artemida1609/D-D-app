package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.proficiency.ProficiencyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProficiencyService {
    ProficiencyDto getByOriginalIndex(String index);

    Page<ProficiencyDto> getAll(Pageable pageable);
}
