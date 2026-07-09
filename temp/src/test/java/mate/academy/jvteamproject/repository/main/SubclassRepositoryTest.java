package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Subclass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSubclass;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubclassRepositoryTest extends TestContainersConfig {
    @Autowired
    private SubclassRepository repository;
    private Subclass subclass;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        subclass = createSubclass("subclass", "Subclass");

        repository.save(subclass);
    }

    @Test
    void getByOriginalIndex_success() {
        Subclass actual = repository.getByOriginalIndex("subclass");

        Assertions.assertEquals(subclass.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(subclass.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Subclass actual = repository.findByNameIgnoreCase("Subclass").orElseThrow();

        Assertions.assertEquals(subclass.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(subclass.getName(), actual.getName());
    }
}
