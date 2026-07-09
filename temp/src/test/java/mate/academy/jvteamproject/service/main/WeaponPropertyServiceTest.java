package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.weapon.WeaponPropertyDto;
import mate.academy.jvteamproject.mapper.main.WeaponPropertyMapper;
import mate.academy.jvteamproject.model.main.WeaponProperty;
import mate.academy.jvteamproject.repository.main.WeaponPropertyRepository;
import mate.academy.jvteamproject.service.impl.main.WeaponPropertyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static mate.academy.jvteamproject.helper.TestDataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeaponPropertyServiceTest {
    @InjectMocks
    private WeaponPropertyServiceImpl service;
    @Mock
    private WeaponPropertyRepository repository;
    @Mock
    private WeaponPropertyMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        WeaponProperty weaponProperty = createWeaponProperty("weapon-property", "Weapon property");
        WeaponPropertyDto expected = createWeaponPropertyDto("weapon-property", "Weapon property");

        when(repository.getByOriginalIndex(weaponProperty.getOriginalIndex())).thenReturn(weaponProperty);
        when(mapper.toDto(weaponProperty)).thenReturn(expected);

        WeaponPropertyDto actual = service.getByOriginalIndex(weaponProperty.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        WeaponProperty first = createWeaponProperty("weapon-property1", "Weapon property1");
        WeaponProperty second = createWeaponProperty("weapon-property2", "Weapon property2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<WeaponPropertyDto> weaponProperties = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, weaponProperties.getContent().size());
    }

    @Test
    void searchByName_success() {
        WeaponProperty weaponProperty = createWeaponProperty("weapon-property", "Weapon property");
        WeaponPropertyDto expected = createWeaponPropertyDto("weapon-property", "Weapon property");

        when(repository.findByNameIgnoreCase(weaponProperty.getName())).thenReturn(Optional.of(weaponProperty));
        when(mapper.toDto(weaponProperty)).thenReturn(expected);

        SearchResult actual = service.searchByName(weaponProperty.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
