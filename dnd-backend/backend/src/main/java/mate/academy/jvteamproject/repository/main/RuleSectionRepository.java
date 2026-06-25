package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.RuleSection;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface RuleSectionRepository extends GeneralRepository<RuleSection> {
    RuleSection getByOriginalIndex(String originalIndex);

    Optional<RuleSection> findByNameIgnoreCase(String name);
}
