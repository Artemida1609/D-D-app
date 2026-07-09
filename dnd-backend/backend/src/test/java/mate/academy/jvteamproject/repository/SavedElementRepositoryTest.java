package mate.academy.jvteamproject.repository;

import java.time.LocalDateTime;
import java.util.List;
import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.SavedElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSavedElement;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SavedElementRepositoryTest extends TestContainersConfig {
    @Autowired
    private SavedElementRepository repository;

    @BeforeEach
    void initData() {
        repository.deleteAll();
        SavedElement firstSavedElement =
                createSavedElement(null, 1L, "Class", 1L, LocalDateTime.now());
        SavedElement secondSavedElement =
                createSavedElement(null, 1L, "Class", 2L, LocalDateTime.now());

        repository.save(firstSavedElement);
        repository.save(secondSavedElement);
    }

    @Test
    void getByUserId_success() {
        int expectedSize = 2;
        List<SavedElement> savedElements = repository.findByUserId(1L);

        Assertions.assertEquals(expectedSize, savedElements.size());

    }
}
