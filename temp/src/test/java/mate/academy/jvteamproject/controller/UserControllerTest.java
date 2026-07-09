package mate.academy.jvteamproject.controller;

import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static mate.academy.jvteamproject.helper.TestDataHelper.createUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends TestContainersConfig {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository repository;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        repository.save(createUser("petro@gmail.com", "1234",
                "Petro", "Petro"));
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    public void deleteUser_success() throws Exception {
        mockMvc.perform(delete("/users/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
