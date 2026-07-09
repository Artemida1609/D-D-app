package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.WeaponProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createWeaponProperty;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WeaponPropertyRepositoryTest extends TestContainersConfig {
    @Autowired
    private WeaponPropertyRepository repository;
    private WeaponProperty weaponProperty;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        weaponProperty = createWeaponProperty("weapon-property", "Weapon property");

        repository.save(weaponProperty);
    }

    @Test
    void getByOriginalIndex_success() {
        WeaponProperty actual = repository.getByOriginalIndex("weapon-property");

        Assertions.assertEquals(weaponProperty.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(weaponProperty.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        WeaponProperty actual = repository.findByNameIgnoreCase("Weapon property").orElseThrow();

        Assertions.assertEquals(weaponProperty.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(weaponProperty.getName(), actual.getName());
    }
}
