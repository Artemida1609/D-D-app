package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.school.MagicSchoolDto;
import mate.academy.jvteamproject.mapper.main.MagicSchoolMapper;
import mate.academy.jvteamproject.model.main.MagicSchool;
import mate.academy.jvteamproject.repository.main.MagicSchoolRepository;
import mate.academy.jvteamproject.service.impl.main.MagicSchoolServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createMagicSchool;
import static mate.academy.jvteamproject.helper.TestDataHelper.createMagicSchoolDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MagicSchoolServiceTest {
    @InjectMocks
    private MagicSchoolServiceImpl service;
    @Mock
    private MagicSchoolRepository repository;
    @Mock
    private MagicSchoolMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        MagicSchool magicSchool = createMagicSchool("magic-school", "Magic school");
        MagicSchoolDto expected = createMagicSchoolDto("magic-school", "Magic school");

        when(repository.getByOriginalIndex(magicSchool.getOriginalIndex())).thenReturn(magicSchool);
        when(mapper.toDto(magicSchool)).thenReturn(expected);

        MagicSchoolDto actual = service.getByOriginalIndex(magicSchool.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        MagicSchool first = createMagicSchool("magic-school1", "Magic school1");
        MagicSchool second = createMagicSchool("magic-school2", "Magic school2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<MagicSchoolDto> magicSchools = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, magicSchools.getContent().size());
    }

    @Test
    void searchByName_success() {
        MagicSchool magicSchool = createMagicSchool("magic-school", "Magic school");
        MagicSchoolDto expected = createMagicSchoolDto("magic-school", "Magic school");

        when(repository.findByNameIgnoreCase(magicSchool.getName())).thenReturn(Optional.of(magicSchool));
        when(mapper.toDto(magicSchool)).thenReturn(expected);

        SearchResult actual = service.searchByName(magicSchool.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
