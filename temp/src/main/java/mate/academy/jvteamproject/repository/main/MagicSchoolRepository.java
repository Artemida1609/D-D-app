package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.MagicSchool;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface MagicSchoolRepository extends GeneralRepository<MagicSchool> {
    MagicSchool getByOriginalIndex(String originalIndex);

    Optional<MagicSchool> findByNameIgnoreCase(String name);

}
