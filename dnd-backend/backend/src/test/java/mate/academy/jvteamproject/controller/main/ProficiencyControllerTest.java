package mate.academy.jvteamproject.controller.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.dto.proficiency.ProficiencyDto;
import mate.academy.jvteamproject.model.main.Proficiency;
import mate.academy.jvteamproject.repository.main.ProficiencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static mate.academy.jvteamproject.helper.TestDataHelper.createProficiency;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProficiencyControllerTest extends TestContainersConfig {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProficiencyRepository repository;

    private Proficiency expected;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        expected = createProficiency("proficiency", "Proficiency");

        repository.save(expected);
    }

    @Test
    void getByIndex_success() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/api/proficiencies/{index}", "proficiency")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        ProficiencyDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                ProficiencyDto.class);

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get("/api/proficiencies")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        JsonNode root = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        ProficiencyDto[] actual = objectMapper.readValue(root.get("content").toString(),
                ProficiencyDto[].class);

        assertEquals(expected.getOriginalIndex(), actual[0].getOriginalIndex());
        assertEquals(expected.getName(), actual[0].getName());
    }
}
