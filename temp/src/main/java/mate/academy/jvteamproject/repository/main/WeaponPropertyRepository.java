package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.WeaponProperty;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface WeaponPropertyRepository extends GeneralRepository<WeaponProperty> {
    WeaponProperty getByOriginalIndex(String originalIndex);

    Optional<WeaponProperty> findByNameIgnoreCase(String name);
}
