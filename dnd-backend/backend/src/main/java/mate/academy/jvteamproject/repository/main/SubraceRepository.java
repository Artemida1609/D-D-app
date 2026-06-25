package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Subrace;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface SubraceRepository extends GeneralRepository<Subrace> {
    Subrace getByOriginalIndex(String originalIndex);

    Optional<Subrace> findByNameIgnoreCase(String name);
}
