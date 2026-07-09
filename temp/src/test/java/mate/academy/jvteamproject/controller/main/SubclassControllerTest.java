package mate.academy.jvteamproject.controller.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.dto.subclass.SubclassDto;
import mate.academy.jvteamproject.model.main.Subclass;
import mate.academy.jvteamproject.repository.main.SubclassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSubclass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SubclassControllerTest extends TestContainersConfig {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SubclassRepository repository;

    private Subclass expected;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        expected = createSubclass("subclass", "Subclass");

        repository.save(expected);
    }

    @Test
    void getByIndex_success() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/api/subclasses/{index}", "subclass")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        SubclassDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                SubclassDto.class);

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get("/api/subclasses")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        JsonNode root = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        SubclassDto[] actual = objectMapper.readValue(root.get("content").toString(),
                SubclassDto[].class);

        assertEquals(expected.getOriginalIndex(), actual[0].getOriginalIndex());
        assertEquals(expected.getName(), actual[0].getName());
    }
}
