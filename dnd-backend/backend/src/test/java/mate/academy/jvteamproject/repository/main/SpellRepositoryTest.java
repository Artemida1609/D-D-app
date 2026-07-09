package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.main.Spell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSpell;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpellRepositoryTest extends TestContainersConfig {
    @Autowired
    private SpellRepository repository;
    private Spell spell;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        spell = createSpell("spell", "Spell");

        repository.save(spell);
    }

    @Test
    void getByOriginalIndex_success() {
        Spell actual = repository.getByOriginalIndex("spell");

        Assertions.assertEquals(spell.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(spell.getName(), actual.getName());
    }

    @Test
    void findByNameIgnoreCase_success() {
        Spell actual = repository.findByNameIgnoreCase("Spell").orElseThrow();

        Assertions.assertEquals(spell.getOriginalIndex(), actual.getOriginalIndex());
        Assertions.assertEquals(spell.getName(), actual.getName());
    }
}
