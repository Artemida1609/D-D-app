package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createCondition;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConditionRepositoryTest extends TestContainersConfig {
    @Autowired
    private ConditionRepository repository;
    private Condition condition;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        condition = createCondition("condition", "Condition");

        repository.save(condition);
    }

    @Test
    void getByOriginalIndex_success() {
        Condition actual = repository.getByOriginalIndex("condition");

        Assertions.assertEquals(condition.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(condition.getName(), actual.getName());
    }

    @Test
    void findByNameLike_success() {
        Condition actual = repository.findByNameLike("Condition").stream().findFirst().orElseThrow();

        Assertions.assertEquals(condition.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(condition.getName(), actual.getName());
    }
}
