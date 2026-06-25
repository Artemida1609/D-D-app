package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.race.RaceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RaceService {
    RaceDto getById(String index);

    Page<RaceDto> getAll(Pageable pageable);
}
