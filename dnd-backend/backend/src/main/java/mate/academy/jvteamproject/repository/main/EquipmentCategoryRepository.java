package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.model.main.EquipmentCategory;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipmentCategoryRepository extends GeneralRepository<EquipmentCategory> {
    Page<EquipmentCategory> findAllByGlobalCategory(String globalCategory, Pageable pageable);
}
