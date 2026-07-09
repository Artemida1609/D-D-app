package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Spell;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface SpellRepository extends GeneralRepository<Spell> {
    Spell getByOriginalIndex(String originalIndex);

    Optional<Spell> findByNameIgnoreCase(String name);

}
