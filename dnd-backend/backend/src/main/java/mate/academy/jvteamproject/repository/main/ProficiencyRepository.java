package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Proficiency;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProficiencyRepository extends GeneralRepository<Proficiency> {
    Proficiency getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT p FROM Proficiency p
            WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Proficiency> findByNameLike(String name);
}
