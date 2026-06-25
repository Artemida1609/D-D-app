package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.DamageType;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface DamageTypeRepository extends GeneralRepository<DamageType> {
    DamageType getByOriginalIndex(String originalIndex);

    Optional<DamageType> findByNameIgnoreCase(String name);
}
