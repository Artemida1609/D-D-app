package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Equipment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createEquipment;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EquipmentRepositoryTest extends TestContainersConfig {
    @Autowired
    private EquipmentRepository repository;
    private Equipment equipment;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        equipment = createEquipment("equipment", "Equipment");

        repository.save(equipment);
    }

    @Test
    void getByOriginalIndex_success() {
        Equipment actual = repository.getByOriginalIndex("equipment");

        Assertions.assertEquals(equipment.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(equipment.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Equipment actual = repository.findByNameIgnoreCase("Equipment").orElseThrow();

        Assertions.assertEquals(equipment.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(equipment.getName(), actual.getName());
    }
}
