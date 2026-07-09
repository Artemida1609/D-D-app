package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.Feature;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface FeatureRepository extends GeneralRepository<Feature> {
    Feature getByOriginalIndex(String originalIndex);

    Optional<Feature> findByNameIgnoreCase(String name);
}
