package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.MagicItem;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createMagicItem;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MagicItemRepositoryTest extends TestContainersConfig {
    @Autowired
    private MagicItemRepository repository;
    private MagicItem magicItem;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        magicItem = createMagicItem("magic-item", "Magic item");

        repository.save(magicItem);
    }

    @Test
    void getByOriginalIndex_success() {
        MagicItem actual = repository.getByOriginalIndex("magic-item");

        Assertions.assertEquals(magicItem.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(magicItem.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        MagicItem actual = repository.findByNameIgnoreCase("Magic item").orElseThrow();

        Assertions.assertEquals(magicItem.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(magicItem.getName(), actual.getName());
    }
}
