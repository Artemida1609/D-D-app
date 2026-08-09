package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Language;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface LanguageRepository extends GeneralRepository<Language> {
    Language getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT l FROM Language l
            WHERE LOWER(l.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Language> findByNameLike(String name);
}
