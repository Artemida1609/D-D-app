package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Condition;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConditionRepository extends GeneralRepository<Condition> {
    Condition getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT c FROM Condition c
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Condition> findByNameLike(String name);
}

