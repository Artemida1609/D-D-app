package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Trait;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface TraitRepository extends GeneralRepository<Trait> {
    Trait getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT t FROM Trait t
            WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Trait> findByNameLike(String name);
}
