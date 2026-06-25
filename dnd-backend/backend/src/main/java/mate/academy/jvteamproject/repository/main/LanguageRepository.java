package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Language;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface LanguageRepository extends GeneralRepository<Language> {
    Language getByOriginalIndex(String originalIndex);

    Optional<Language> findByNameIgnoreCase(String name);

}
