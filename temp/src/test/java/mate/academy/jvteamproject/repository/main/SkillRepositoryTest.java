package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Skill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSkill;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SkillRepositoryTest extends TestContainersConfig {
    @Autowired
    private SkillRepository repository;
    private Skill skill;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        skill = createSkill("skill", "Skill");

        repository.save(skill);
    }

    @Test
    void getByOriginalIndex_success() {
        Skill actual = repository.getByOriginalIndex("skill");

        Assertions.assertEquals(skill.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(skill.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Skill actual = repository.findByNameIgnoreCase("Skill").orElseThrow();

        Assertions.assertEquals(skill.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(skill.getName(), actual.getName());
    }
}
