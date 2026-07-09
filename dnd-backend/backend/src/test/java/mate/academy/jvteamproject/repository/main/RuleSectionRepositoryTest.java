package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.RuleSection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createRuleSection;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RuleSectionRepositoryTest extends TestContainersConfig {
    @Autowired
    private RuleSectionRepository repository;
    private RuleSection ruleSection;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        ruleSection = createRuleSection("rule-section", "Rule section");

        repository.save(ruleSection);
    }

    @Test
    void getByOriginalIndex_success() {
        RuleSection actual = repository.getByOriginalIndex("rule-section");

        Assertions.assertEquals(ruleSection.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(ruleSection.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        RuleSection actual = repository.findByNameIgnoreCase("Rule section").orElseThrow();

        Assertions.assertEquals(ruleSection.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(ruleSection.getName(), actual.getName());
    }
}
