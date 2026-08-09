package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Race;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface RaceRepository extends GeneralRepository<Race> {
    Race getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT r FROM Race r
            WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Race> findByNameLike(String name);
}
