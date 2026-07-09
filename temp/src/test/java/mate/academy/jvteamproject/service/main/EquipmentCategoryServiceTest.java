package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import mate.academy.jvteamproject.dto.equipment.EquipmentCategoryDto;
import mate.academy.jvteamproject.mapper.main.EquipmentCategoryMapper;
import mate.academy.jvteamproject.model.main.EquipmentCategory;
import mate.academy.jvteamproject.repository.main.EquipmentCategoryRepository;
import mate.academy.jvteamproject.service.impl.main.EquipmentCategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createEquipmentCategory;
import static mate.academy.jvteamproject.helper.TestDataHelper.createEquipmentCategoryDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EquipmentCategoryServiceTest {
    @InjectMocks
    private EquipmentCategoryServiceImpl service;
    @Mock
    private EquipmentCategoryRepository repository;
    @Mock
    private EquipmentCategoryMapper mapper;

    @Test
    void getAllByCategory_success() {
        EquipmentCategory equipmentCategory =
                createEquipmentCategory("equipment-category", "armor");
        EquipmentCategoryDto equipmentCategoryDto =
                createEquipmentCategoryDto("equipment-category", "armor");
        int expectedSize = 1;
        when(repository.findAllByGlobalCategory("armor", PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(equipmentCategory)));
        when(mapper.toDto(equipmentCategory))
                .thenReturn(equipmentCategoryDto);
        Page<EquipmentCategoryDto> equipments = service.getAllByCategory("armor", PageRequest.of(0, 10));

        assertEquals(expectedSize, equipments.getContent().size());
    }
}
