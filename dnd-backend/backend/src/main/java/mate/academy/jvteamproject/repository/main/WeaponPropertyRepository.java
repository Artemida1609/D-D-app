package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.WeaponProperty;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface WeaponPropertyRepository extends GeneralRepository<WeaponProperty> {
    WeaponProperty getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT wProperty FROM WeaponProperty wProperty
            WHERE LOWER(wProperty.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<WeaponProperty> findByNameLike(String name);
}
