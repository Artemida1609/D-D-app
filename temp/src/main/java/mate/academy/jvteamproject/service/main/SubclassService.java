package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.subclass.SubclassDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubclassService {
    SubclassDto getByOriginalIndex(String index);

    Page<SubclassDto> getAll(Pageable pageable);
}
