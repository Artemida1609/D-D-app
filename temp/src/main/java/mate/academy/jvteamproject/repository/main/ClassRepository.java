package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Class;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface ClassRepository extends GeneralRepository<Class> {
    Class getByOriginalIndex(String originalIndex);

    Optional<Class> findByNameIgnoreCase(String name);
}
