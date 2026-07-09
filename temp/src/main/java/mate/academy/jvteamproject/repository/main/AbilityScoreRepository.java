package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.AbilityScore;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface AbilityScoreRepository extends GeneralRepository<AbilityScore> {
    AbilityScore getByOriginalIndex(String originalIndex);

    Optional<AbilityScore> findByNameIgnoreCase(String name);
}
