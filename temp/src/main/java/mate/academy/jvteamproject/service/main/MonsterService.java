package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.monster.MonsterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MonsterService {
    MonsterDto getByOriginalIndex(String index);

    Page<MonsterDto> getAll(Pageable pageable);
}
