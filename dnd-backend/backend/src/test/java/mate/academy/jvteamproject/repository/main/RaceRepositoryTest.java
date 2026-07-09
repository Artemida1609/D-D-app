package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Race;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createRace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RaceRepositoryTest extends TestContainersConfig {
    @Autowired
    private RaceRepository repository;
    private Race race;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        race = createRace("race", "Race");

        repository.save(race);
    }

    @Test
    void getByOriginalIndex_success() {
        Race actual = repository.getByOriginalIndex("race");

        Assertions.assertEquals(race.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(race.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Race actual = repository.findByNameIgnoreCase("Race").orElseThrow();

        Assertions.assertEquals(race.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(race.getName(), actual.getName());
    }
}
