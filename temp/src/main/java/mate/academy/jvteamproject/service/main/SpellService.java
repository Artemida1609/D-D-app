package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.spell.SpellDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpellService {
    SpellDto getByOriginalIndex(String index);

    Page<SpellDto> getAll(Pageable pageable);
}
