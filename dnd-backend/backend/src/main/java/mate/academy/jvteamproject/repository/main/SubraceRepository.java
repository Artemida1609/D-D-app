package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Subrace;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubraceRepository extends GeneralRepository<Subrace> {
    Subrace getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT s FROM Subrace s
            WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Subrace> findByNameLike(String name);
}
