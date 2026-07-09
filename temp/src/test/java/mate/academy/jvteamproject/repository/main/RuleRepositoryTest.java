package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createRule;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RuleRepositoryTest extends TestContainersConfig {
    @Autowired
    private RuleRepository repository;
    private Rule rule;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        rule = createRule("rule", "Rule");

        repository.save(rule);
    }

    @Test
    void getByOriginalIndex_success() {
        Rule actual = repository.getByOriginalIndex("rule");

        Assertions.assertEquals(rule.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(rule.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Rule actual = repository.findByNameIgnoreCase("Rule").orElseThrow();

        Assertions.assertEquals(rule.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(rule.getName(), actual.getName());
    }
}
