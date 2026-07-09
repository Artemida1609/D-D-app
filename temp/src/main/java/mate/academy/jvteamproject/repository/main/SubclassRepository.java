package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Subclass;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface SubclassRepository extends GeneralRepository<Subclass> {
    Subclass getByOriginalIndex(String originalIndex);

    Optional<Subclass> findByNameIgnoreCase(String name);
}
