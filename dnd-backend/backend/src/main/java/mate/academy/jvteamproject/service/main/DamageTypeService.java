package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.damage.DamageTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DamageTypeService {
    DamageTypeDto getById(String index);

    Page<DamageTypeDto> getAll(Pageable pageable);
}
