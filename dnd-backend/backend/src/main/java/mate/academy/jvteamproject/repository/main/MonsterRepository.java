package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Monster;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface MonsterRepository extends GeneralRepository<Monster> {
    Monster getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT m FROM Monster m
            WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Monster> findByNameLike(String name);
}
