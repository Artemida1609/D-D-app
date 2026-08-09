package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.RuleSection;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface RuleSectionRepository extends GeneralRepository<RuleSection> {
    RuleSection getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT rSection FROM RuleSection rSection
            WHERE LOWER(rSection.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<RuleSection> findByNameLike(String name);
}
