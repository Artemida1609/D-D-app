package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Skill;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface SkillRepository extends GeneralRepository<Skill> {
    Skill getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT s FROM Skill s
            WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Skill> findByNameLike(String name);
}
