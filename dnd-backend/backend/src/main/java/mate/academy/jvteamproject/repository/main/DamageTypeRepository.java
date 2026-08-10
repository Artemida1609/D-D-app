package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.DamageType;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface DamageTypeRepository extends GeneralRepository<DamageType> {
    DamageType getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT damage FROM DamageType damage
            WHERE LOWER(damage.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<DamageType> findByNameLike(String name);
}
