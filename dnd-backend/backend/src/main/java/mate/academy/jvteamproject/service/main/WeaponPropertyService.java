package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.weapon.WeaponPropertyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WeaponPropertyService {
    WeaponPropertyDto getById(String index);

    Page<WeaponPropertyDto> getAll(Pageable pageable);
}
