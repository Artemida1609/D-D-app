package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Condition;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface ConditionRepository extends GeneralRepository<Condition> {
    Condition getByOriginalIndex(String originalIndex);

    Optional<Condition> findByNameIgnoreCase(String name);
}
