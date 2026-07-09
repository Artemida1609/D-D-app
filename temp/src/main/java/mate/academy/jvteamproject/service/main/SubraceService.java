package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.subrace.SubraceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubraceService {
    SubraceDto getByOriginalIndex(String index);

    Page<SubraceDto> getAll(Pageable pageable);
}
