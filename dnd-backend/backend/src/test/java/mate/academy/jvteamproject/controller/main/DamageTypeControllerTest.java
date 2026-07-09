package mate.academy.jvteamproject.controller.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.dto.damage.DamageTypeDto;
import mate.academy.jvteamproject.model.main.DamageType;
import mate.academy.jvteamproject.repository.main.DamageTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static mate.academy.jvteamproject.helper.TestDataHelper.createDamageType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DamageTypeControllerTest extends TestContainersConfig {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DamageTypeRepository repository;

    private DamageType expected;

    @BeforeEach
    void initData() {
        repository.deleteAll();

        expected = createDamageType("damage-type", "Damage type");

        repository.save(expected);
    }

    @Test
    void getByIndex_success() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/api/damage-types/{index}", "damage-type")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        DamageTypeDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                DamageTypeDto.class);

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get("/api/damage-types")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        JsonNode root = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        DamageTypeDto[] actual = objectMapper.readValue(root.get("content").toString(),
                DamageTypeDto[].class);

        assertEquals(expected.getOriginalIndex(), actual[0].getOriginalIndex());
        assertEquals(expected.getName(), actual[0].getName());
    }
}
