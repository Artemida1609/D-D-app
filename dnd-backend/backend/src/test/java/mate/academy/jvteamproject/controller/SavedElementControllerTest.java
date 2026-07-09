package mate.academy.jvteamproject.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.dto.saved.SavedElementRequestDto;
import mate.academy.jvteamproject.dto.saved.SavedElementResponseDto;
import mate.academy.jvteamproject.model.SavedElement;
import mate.academy.jvteamproject.model.User;
import mate.academy.jvteamproject.repository.SavedElementRepository;
import mate.academy.jvteamproject.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static mate.academy.jvteamproject.helper.TestDataHelper.createSavedElement;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSavedElementRequestDto;
import static mate.academy.jvteamproject.helper.TestDataHelper.createUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SavedElementControllerTest extends TestContainersConfig {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SavedElementRepository repository;
    @Autowired
    private UserRepository userRepository;

    private SavedElement expected;

    @BeforeEach
    void initData() {
        repository.deleteAll();
        userRepository.deleteAll();

        User user = createUser("email", "password",
                "firstName", "lastName");
        userRepository.save(user);

        expected = createSavedElement(null, user.getId(), "Class", 1L, LocalDateTime.now());
        repository.save(expected);
    }

    @WithMockUser(username = "email", roles = "USER")
    @Test
    void add_success() throws Exception {
        SavedElementRequestDto requestDto = createSavedElementRequestDto("Class", 1L);
        MvcResult mvcResult = mockMvc.perform(post("/saved/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andReturn();

        SavedElementResponseDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                SavedElementResponseDto.class);

        assertEquals(requestDto.getEntityId(), actual.getEntityId());
        assertEquals(requestDto.getEntityType(), actual.getEntityType());
    }

    @WithMockUser(username = "email", roles = "USER")
    @Test
    void getAll_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get("/saved")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        JsonNode root = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        SavedElementResponseDto[] actual = objectMapper.readValue(root.toString(), SavedElementResponseDto[].class);

        assertEquals(expected.getEntityType(), actual[0].getEntityType());
        assertEquals(expected.getEntityId(), actual[0].getEntityId());
    }

    @WithMockUser(username = "email", roles = "USER")
    @Test
    void remove_success() throws Exception{
        mockMvc.perform(delete("/saved/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
