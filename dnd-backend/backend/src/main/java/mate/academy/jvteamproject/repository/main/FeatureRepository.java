package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.Feature;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeatureRepository extends GeneralRepository<Feature> {
    Feature getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT f FROM Feature f
            WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Feature> findByNameLike(String name);
}
