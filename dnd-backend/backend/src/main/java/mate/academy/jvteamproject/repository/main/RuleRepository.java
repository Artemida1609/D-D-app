package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Rule;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface RuleRepository extends GeneralRepository<Rule> {
    Rule getByOriginalIndex(String originalIndex);

    Optional<Rule> findByNameIgnoreCase(String name);
}
