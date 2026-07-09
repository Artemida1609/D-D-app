package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.equipment.EquipmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipmentService {
    EquipmentDto getByOriginalIndex(String index);

    Page<EquipmentDto> getAll(Pageable pageable);
}
