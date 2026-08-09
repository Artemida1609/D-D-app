package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Class;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassRepository extends GeneralRepository<Class> {
    Class getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT c FROM Class c
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Class> findByNameLike(String name);
}
