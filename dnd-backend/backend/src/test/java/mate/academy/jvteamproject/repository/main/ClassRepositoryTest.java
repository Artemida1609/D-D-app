package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Class;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createClass;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClassRepositoryTest extends TestContainersConfig {
    @Autowired
    private ClassRepository repository;
    private Class classEntity;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        classEntity = createClass("class", "Class");

        repository.save(classEntity);
    }

    @Test
    void getByOriginalIndex_success() {
        Class actual = repository.getByOriginalIndex("class");

        Assertions.assertEquals(classEntity.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(classEntity.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Class actual = repository.findByNameLike("Class").stream().findFirst().orElseThrow();

        Assertions.assertEquals(classEntity.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(classEntity.getName(), actual.getName());
    }
}
