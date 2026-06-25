package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Race;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface RaceRepository extends GeneralRepository<Race> {
    Race getByOriginalIndex(String originalIndex);

    Optional<Race> findByNameIgnoreCase(String name);
}
