package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Proficiency;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface ProficiencyRepository extends GeneralRepository<Proficiency> {
    Proficiency getByOriginalIndex(String originalIndex);

    Optional<Proficiency> findByNameIgnoreCase(String name);
}
