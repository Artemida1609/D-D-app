package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Trait;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface TraitRepository extends GeneralRepository<Trait> {
    Trait getByOriginalIndex(String originalIndex);

    Optional<Trait> findByNameIgnoreCase(String name);
}
