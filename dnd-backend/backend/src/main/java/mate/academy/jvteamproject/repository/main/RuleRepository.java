package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Rule;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface RuleRepository extends GeneralRepository<Rule> {
    Rule getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT r FROM Rule r
            WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Rule> findByNameLike(String name);
}
