package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.MagicSchool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static mate.academy.jvteamproject.helper.TestDataHelper.createMagicSchool;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MagicSchoolRepositoryTest extends TestContainersConfig {
    @Autowired
    private MagicSchoolRepository repository;
    private MagicSchool magicSchool;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        magicSchool = createMagicSchool("magic-school", "Magic school");

        repository.save(magicSchool);
    }

    @Test
    void getByOriginalIndex_success() {
        MagicSchool actual = repository.getByOriginalIndex("magic-school");

        Assertions.assertEquals(magicSchool.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(magicSchool.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        MagicSchool actual = repository.findByNameLike("Magic school").stream().findFirst().orElseThrow();

        Assertions.assertEquals(magicSchool.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(magicSchool.getName(), actual.getName());
    }
}
