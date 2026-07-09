package mate.academy.jvteamproject.controller.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.dto.equipment.EquipmentCategoryDto;
import mate.academy.jvteamproject.dto.equipment.EquipmentDto;
import mate.academy.jvteamproject.model.main.Equipment;
import mate.academy.jvteamproject.model.main.EquipmentCategory;
import mate.academy.jvteamproject.repository.main.EquipmentCategoryRepository;
import mate.academy.jvteamproject.repository.main.EquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static mate.academy.jvteamproject.helper.TestDataHelper.createEquipment;
import static mate.academy.jvteamproject.helper.TestDataHelper.createEquipmentCategory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EquipmentCategoryControllerTest extends TestContainersConfig {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EquipmentCategoryRepository categoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    private Equipment equipmentExpected;
    private EquipmentCategory categoryExpected;

    @BeforeEach
    void initData() {
        categoryRepository.deleteAll();
        equipmentRepository.deleteAll();

        categoryExpected = createEquipmentCategory("equipment-category", "armor");
        equipmentExpected = createEquipment("equipment", "Equipment");

        equipmentRepository.save(equipmentExpected);
        categoryRepository.save(categoryExpected);
    }

    @Test
    void getByIndex_success() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/api/equipment-categories/equipment/{index}", "equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        EquipmentDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                EquipmentDto.class);

        assertEquals(equipmentExpected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(equipmentExpected.getName(), actual.getName());
    }

    @Test
    void getAllByCategory_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get("/api/equipment-categories/{category}", "armor")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        JsonNode root = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        EquipmentCategoryDto[] actual = objectMapper.readValue(root.get("content").toString(),
                EquipmentCategoryDto[].class);

        assertEquals(categoryExpected.getOriginalIndex(), actual[0].getOriginalIndex());
        assertEquals(categoryExpected.getName(), actual[0].getName());
    }
}
