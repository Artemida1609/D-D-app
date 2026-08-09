package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Subrace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSubrace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubraceRepositoryTest extends TestContainersConfig {
    @Autowired
    private SubraceRepository repository;
    private Subrace subrace;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        subrace = createSubrace("subrace", "Subrace");

        repository.save(subrace);
    }

    @Test
    void getByOriginalIndex_success() {
        Subrace actual = repository.getByOriginalIndex("subrace");

        Assertions.assertEquals(subrace.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(subrace.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Subrace actual = repository.findByNameLike("Subrace").stream().findFirst().orElseThrow();

        Assertions.assertEquals(subrace.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(subrace.getName(), actual.getName());
    }
}
