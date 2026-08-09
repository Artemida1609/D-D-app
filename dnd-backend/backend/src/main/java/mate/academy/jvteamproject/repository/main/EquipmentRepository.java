package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Equipment;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquipmentRepository extends GeneralRepository<Equipment> {
    Equipment getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT e FROM Equipment e
            WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Equipment> findByNameLike(String name);
}
