package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.equipment.EquipmentDto;
import mate.academy.jvteamproject.mapper.main.EquipmentMapper;
import mate.academy.jvteamproject.model.main.Equipment;
import mate.academy.jvteamproject.repository.main.EquipmentRepository;
import mate.academy.jvteamproject.service.impl.main.EquipmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createEquipment;
import static mate.academy.jvteamproject.helper.TestDataHelper.createEquipmentDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EquipmentServiceTest {
    @InjectMocks
    private EquipmentServiceImpl service;
    @Mock
    private EquipmentRepository repository;
    @Mock
    private EquipmentMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Equipment equipment = createEquipment("equipment", "Equipment");
        EquipmentDto expected = createEquipmentDto("equipment", "Equipment");

        when(repository.getByOriginalIndex(equipment.getOriginalIndex())).thenReturn(equipment);
        when(mapper.toDto(equipment)).thenReturn(expected);

        EquipmentDto actual = service.getByOriginalIndex(equipment.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Equipment first = createEquipment("equipment1", "Equipment2");
        Equipment second = createEquipment("equipment1", "Equipment2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<EquipmentDto> tasks = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, tasks.getContent().size());
    }

    @Test
    void searchByName_success() {
        Equipment equipment = createEquipment("equipment", "Equipment");
        EquipmentDto expected = createEquipmentDto("equipment", "Equipment");

        when(repository.findByNameIgnoreCase(equipment.getName())).thenReturn(Optional.of(equipment));
        when(mapper.toDto(equipment)).thenReturn(expected);

        SearchResult actual = service.searchByName(equipment.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
