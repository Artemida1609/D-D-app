package mate.academy.jvteamproject;

import mate.academy.jvteamproject.config.TestContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
class JvTeamProjectApplicationTests extends TestContainersConfig {

    @Test
    void contextLoads() {
    }

}
