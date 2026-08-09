package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.AbilityScore;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface AbilityScoreRepository extends GeneralRepository<AbilityScore> {
    AbilityScore getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT ability FROM AbilityScore ability
            WHERE LOWER(ability.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<AbilityScore> findByNameLike(String name);
}
