package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.trait.TraitDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TraitService {
    TraitDto getByOriginalIndex(String index);

    Page<TraitDto> getAll(Pageable pageable);
}
