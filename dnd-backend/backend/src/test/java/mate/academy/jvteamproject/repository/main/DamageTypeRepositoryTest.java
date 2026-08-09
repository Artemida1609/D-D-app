package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.DamageType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createDamageType;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DamageTypeRepositoryTest extends TestContainersConfig {
    @Autowired
    private DamageTypeRepository repository;
    private DamageType damageType;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        damageType = createDamageType("damage-type", "Damage type");

        repository.save(damageType);
    }

    @Test
    void getByOriginalIndex_success() {
        DamageType actual = repository.getByOriginalIndex("damage-type");

        Assertions.assertEquals(damageType.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(damageType.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        DamageType actual = repository.findByNameLike("Damage type").stream().findFirst().orElseThrow();

        Assertions.assertEquals(damageType.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(damageType.getName(), actual.getName());
    }
}
