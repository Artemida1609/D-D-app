package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.MagicSchool;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface MagicSchoolRepository extends GeneralRepository<MagicSchool> {
    MagicSchool getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT mSchool FROM MagicSchool mSchool
            WHERE LOWER(mSchool.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<MagicSchool> findByNameLike(String name);

}
