package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.EquipmentCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static mate.academy.jvteamproject.helper.TestDataHelper.createEquipmentCategory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EquipmentCategoryRepositoryTest extends TestContainersConfig {
    @Autowired
    private EquipmentCategoryRepository repository;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        EquipmentCategory firstEquipmentCategory =
                createEquipmentCategory("equipment-category1", "armor");
        EquipmentCategory secondEquipmentCategory =
                createEquipmentCategory("equipment-category2", "armor");

        repository.save(firstEquipmentCategory);
        repository.save(secondEquipmentCategory);
    }

    @Test
    void findAllByGlobalCategory_success() {
        int expectedSize = 2;
        Pageable pageable = PageRequest.of(0, 10);
        Page<EquipmentCategory> actual = repository.findAllByGlobalCategory("armor", pageable);

        Assertions.assertEquals(expectedSize, actual.getTotalElements());
    }
}
