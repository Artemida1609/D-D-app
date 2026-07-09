package mate.academy.jvteamproject.config;

import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TestContainersConfig {

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", GlobalTestContainer.MYSQL::getJdbcUrl);
        registry.add("spring.datasource.username", GlobalTestContainer.MYSQL::getUsername);
        registry.add("spring.datasource.password", GlobalTestContainer.MYSQL::getPassword);
    }
}
