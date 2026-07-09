package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.damage.DamageTypeDto;
import mate.academy.jvteamproject.mapper.main.DamageTypeMapper;
import mate.academy.jvteamproject.model.main.DamageType;
import mate.academy.jvteamproject.repository.main.DamageTypeRepository;
import mate.academy.jvteamproject.service.impl.main.DamageTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createDamageType;
import static mate.academy.jvteamproject.helper.TestDataHelper.createDamageTypeDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DamageTypeServiceTest {
    @InjectMocks
    private DamageTypeServiceImpl service;
    @Mock
    private DamageTypeRepository repository;
    @Mock
    private DamageTypeMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        DamageType damageType = createDamageType("damage-type", "Damage Type");
        DamageTypeDto expected = createDamageTypeDto("damage-type", "Damage Type");

        when(repository.getByOriginalIndex(damageType.getOriginalIndex())).thenReturn(damageType);
        when(mapper.toDto(damageType)).thenReturn(expected);

        DamageTypeDto actual = service.getByOriginalIndex(damageType.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        DamageType first = createDamageType("damage-type1", "Damage Type1");
        DamageType second = createDamageType("damage-type2", "Damage Type2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<DamageTypeDto> damageTypes = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, damageTypes.getContent().size());
    }

    @Test
    void searchByName_success() {
        DamageType damageType = createDamageType("damage-type", "Damage Type");
        DamageTypeDto expected = createDamageTypeDto("damage-type", "Damage Type");

        when(repository.findByNameIgnoreCase(damageType.getName())).thenReturn(Optional.of(damageType));
        when(mapper.toDto(damageType)).thenReturn(expected);

        SearchResult actual = service.searchByName(damageType.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
