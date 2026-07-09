package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.magic.MagicItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MagicItemService {
    MagicItemDto getByOriginalIndex(String index);

    Page<MagicItemDto> getAll(Pageable pageable);
}
