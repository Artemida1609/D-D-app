package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Equipment;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface EquipmentRepository extends GeneralRepository<Equipment> {
    Equipment getByOriginalIndex(String originalIndex);

    Optional<Equipment> findByNameIgnoreCase(String name);
}
