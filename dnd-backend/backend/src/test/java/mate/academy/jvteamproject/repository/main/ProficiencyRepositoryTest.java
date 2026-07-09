package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Proficiency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createProficiency;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProficiencyRepositoryTest extends TestContainersConfig {
    @Autowired
    private ProficiencyRepository repository;
    private Proficiency proficiency;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        proficiency = createProficiency("proficiency", "Proficiency");

        repository.save(proficiency);
    }

    @Test
    void getByOriginalIndex_success() {
        Proficiency actual = repository.getByOriginalIndex("proficiency");

        Assertions.assertEquals(proficiency.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(proficiency.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Proficiency actual = repository.findByNameIgnoreCase("Proficiency").orElseThrow();

        Assertions.assertEquals(proficiency.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(proficiency.getName(), actual.getName());
    }
}
