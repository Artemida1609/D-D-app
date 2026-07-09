package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static mate.academy.jvteamproject.helper.TestDataHelper.createLevel;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LevelRepositoryTest extends TestContainersConfig {
    @Autowired
    private LevelRepository repository;

    private Level firstLevelEntity;
    private Level secondLevelEntity;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        firstLevelEntity = createLevel("level", 1);
        secondLevelEntity = createLevel("level", 2);

        repository.save(firstLevelEntity);
        repository.save(secondLevelEntity);
    }

    @Test
    void getLevelByOriginalIndex_success() {
        Level actual = repository.getLevelByOriginalIndex("level", 1);

        Assertions.assertEquals(firstLevelEntity.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(firstLevelEntity.getLevel(), actual.getLevel());
    }

    @Test
    void getAllLevelByOriginalIndex_success() {
        int expectedSize = 2;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Level> actual = repository.getAllLevelsByOriginalIndex("level", pageable);

        Assertions.assertEquals(expectedSize, actual.getTotalElements());
    }
}
