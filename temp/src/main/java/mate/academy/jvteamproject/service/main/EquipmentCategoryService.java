package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.equipment.EquipmentCategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipmentCategoryService {
    Page<EquipmentCategoryDto> getAllByCategory(String globalCategory, Pageable pageable);
}
