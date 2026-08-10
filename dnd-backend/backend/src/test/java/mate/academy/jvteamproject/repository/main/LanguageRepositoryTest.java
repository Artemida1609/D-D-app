package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Language;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createLanguage;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LanguageRepositoryTest extends TestContainersConfig {
    @Autowired
    private LanguageRepository repository;
    private Language language;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        language = createLanguage("language", "Language");

        repository.save(language);
    }

    @Test
    void getByOriginalIndex_success() {
        Language actual = repository.getByOriginalIndex("language");

        Assertions.assertEquals(language.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(language.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Language actual = repository.findByNameLike("Language").stream().findFirst().orElseThrow();

        Assertions.assertEquals(language.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(language.getName(), actual.getName());
    }
}
