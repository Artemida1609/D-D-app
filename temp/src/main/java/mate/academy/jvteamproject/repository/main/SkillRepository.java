package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Skill;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface SkillRepository extends GeneralRepository<Skill> {
    Skill getByOriginalIndex(String originalIndex);

    Optional<Skill> findByNameIgnoreCase(String name);
}
