package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Monster;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface MonsterRepository extends GeneralRepository<Monster> {
    Monster getByOriginalIndex(String originalIndex);

    Optional<Monster> findByNameIgnoreCase(String name);
}
