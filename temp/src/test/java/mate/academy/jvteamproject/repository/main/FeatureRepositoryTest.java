package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createFeature;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FeatureRepositoryTest extends TestContainersConfig {
    @Autowired
    private FeatureRepository repository;
    private Feature feature;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        feature = createFeature("feature", "Feature");

        repository.save(feature);
    }

    @Test
    void getByOriginalIndex_success() {
        Feature actual = repository.getByOriginalIndex("feature");

        Assertions.assertEquals(feature.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(feature.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Feature actual = repository.findByNameIgnoreCase("Feature").orElseThrow();

        Assertions.assertEquals(feature.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(feature.getName(), actual.getName());
    }
}
