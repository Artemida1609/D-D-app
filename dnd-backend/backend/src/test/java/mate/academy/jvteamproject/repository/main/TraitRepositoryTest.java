package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Trait;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createTrait;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TraitRepositoryTest extends TestContainersConfig {
    @Autowired
    private TraitRepository repository;
    private Trait trait;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        trait = createTrait("trait", "Trait");

        repository.save(trait);
    }

    @Test
    void getByOriginalIndex_success() {
        Trait actual = repository.getByOriginalIndex("trait");

        Assertions.assertEquals(trait.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(trait.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Trait actual = repository.findByNameLike("Trait").stream().findFirst().orElseThrow();

        Assertions.assertEquals(trait.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(trait.getName(), actual.getName());
    }
}
