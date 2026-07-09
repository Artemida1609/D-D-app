package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.AbilityScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createAbilityScore;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AbilityScoreRepositoryTest extends TestContainersConfig {
    @Autowired
    private AbilityScoreRepository repository;
    private AbilityScore abilityScore;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        abilityScore = createAbilityScore("ability-score", "Ability Score");

        repository.save(abilityScore);
    }

    @Test
    void getByOriginalIndex_success() {
        AbilityScore actual = repository.getByOriginalIndex("ability-score");

        Assertions.assertEquals(abilityScore.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(abilityScore.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        AbilityScore actual = repository.findByNameIgnoreCase("Ability Score").orElseThrow();

        Assertions.assertEquals(abilityScore.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(abilityScore.getName(), actual.getName());
    }
}
