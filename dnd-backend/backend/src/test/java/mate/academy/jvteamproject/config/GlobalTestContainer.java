package mate.academy.jvteamproject.config;

import org.testcontainers.containers.MySQLContainer;

public class GlobalTestContainer {

    public static final MySQLContainer<?> MYSQL =
            new MySQLContainer<>("mysql:8")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test")
                    .withReuse(false);

    static {
        MYSQL.start();
    }
}
