package mate.academy.jvteamproject.repository;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createUser;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest extends TestContainersConfig {
    @Autowired
    private UserRepository repository;
    private User user;

    @BeforeAll
    void initData() {
        repository.deleteAll();

        user = createUser("petropetro@gmail.com", "1234",
                "nickname1");

        repository.save(user);
    }

    @Test
    void findByEmail_success() {
        User actual = repository.findByEmail("petropetro@gmail.com").orElseThrow();

        Assertions.assertEquals(user.getId(), actual.getId());
        Assertions.assertEquals(user.getEmail(), actual.getEmail());
        Assertions.assertEquals(user.getPassword(), actual.getPassword());
        Assertions.assertEquals(user.getUserNickname(), actual.getUserNickname());
    }

    @Test
    void getUserByEmail_success() {
        User actual = repository.getUserByEmail("petropetro@gmail.com");

        Assertions.assertEquals(user.getId(), actual.getId());
        Assertions.assertEquals(user.getEmail(), actual.getEmail());
        Assertions.assertEquals(user.getPassword(), actual.getPassword());
        Assertions.assertEquals(user.getUserNickname(), actual.getUserNickname());
    }
}
