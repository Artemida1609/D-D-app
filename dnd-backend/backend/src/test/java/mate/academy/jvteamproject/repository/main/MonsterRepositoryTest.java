package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createMonster;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MonsterRepositoryTest extends TestContainersConfig {
    @Autowired
    private MonsterRepository repository;
    private Monster monster;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        monster = createMonster("monster","Monster");

        repository.save(monster);
    }

    @Test
    void getByOriginalIndex_success() {
        Monster actual = repository.getByOriginalIndex("monster");

        Assertions.assertEquals(monster.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(monster.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Monster actual = repository.findByNameLike("Monster").stream().findFirst().orElseThrow();

        Assertions.assertEquals(monster.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(monster.getName(), actual.getName());
    }
}
