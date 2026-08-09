package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Spell;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpellRepository extends GeneralRepository<Spell> {
    Spell getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT s FROM Spell s
            WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Spell> findByNameLike(String name);
}
