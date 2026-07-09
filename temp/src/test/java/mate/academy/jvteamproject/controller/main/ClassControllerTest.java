package mate.academy.jvteamproject.controller.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.dto.level.LevelDto;
import mate.academy.jvteamproject.model.main.Class;
import mate.academy.jvteamproject.model.main.Level;
import mate.academy.jvteamproject.repository.main.ClassRepository;
import mate.academy.jvteamproject.repository.main.LevelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static mate.academy.jvteamproject.helper.TestDataHelper.createClass;
import static mate.academy.jvteamproject.helper.TestDataHelper.createLevel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassControllerTest extends TestContainersConfig {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private LevelRepository levelRepository;

    private Class classExpected;
    private Level levelExpected;

    @BeforeEach
    void initData() {
        classRepository.deleteAll();
        levelRepository.deleteAll();

        classExpected = createClass("class", "Class");
        levelExpected = createLevel("level", 1);

        classRepository.save(classExpected);
        levelRepository.save(levelExpected);
    }

    @Test
    void getByIndex_success() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/api/classes/{index}", "class")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        ClassDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                ClassDto.class);

        assertEquals(classExpected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(classExpected.getName(), actual.getName());
    }

    @Test
    void getAll_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get("/api/classes")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        JsonNode root = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        ClassDto[] actual = objectMapper.readValue(root.get("content").toString(),
                ClassDto[].class);

        assertEquals(classExpected.getOriginalIndex(), actual[0].getOriginalIndex());
        assertEquals(classExpected.getName(), actual[0].getName());
    }

    @Test
    void getLevelByIdAndLevel_success() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/api/classes/{index}/level/{level}", "level", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        LevelDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                LevelDto.class);

        assertEquals(levelExpected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(levelExpected.getLevel(), actual.getLevel());
    }

    @Test
    void getAllLevelsById_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get("/api/classes/{index}/level", "level")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        JsonNode root = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        LevelDto[] actual = objectMapper.readValue(root.get("content").toString(),
                LevelDto[].class);

        assertEquals(levelExpected.getOriginalIndex(), actual[0].getOriginalIndex());
        assertEquals(levelExpected.getLevel(), actual[0].getLevel());
    }
}
