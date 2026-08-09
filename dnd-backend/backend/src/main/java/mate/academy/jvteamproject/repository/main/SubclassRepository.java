package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Subclass;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubclassRepository extends GeneralRepository<Subclass> {
    Subclass getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT s FROM Subclass s
            WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Subclass> findByNameLike(String name);
}
