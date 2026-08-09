package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.proficiency.ProficiencyDto;
import mate.academy.jvteamproject.mapper.main.ProficiencyMapper;
import mate.academy.jvteamproject.model.main.Proficiency;
import mate.academy.jvteamproject.repository.main.ProficiencyRepository;
import mate.academy.jvteamproject.service.impl.main.ProficiencyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createProficiency;
import static mate.academy.jvteamproject.helper.TestDataHelper.createProficiencyDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProficiencyServiceTest {
    @InjectMocks
    private ProficiencyServiceImpl service;
    @Mock
    private ProficiencyRepository repository;
    @Mock
    private ProficiencyMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Proficiency proficiency = createProficiency("proficiency", "Proficiency");
        ProficiencyDto expected = createProficiencyDto("proficiency", "Proficiency");

        when(repository.getByOriginalIndex(proficiency.getOriginalIndex())).thenReturn(proficiency);
        when(mapper.toDto(proficiency)).thenReturn(expected);

        ProficiencyDto actual = service.getByOriginalIndex(proficiency.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Proficiency first = createProficiency("proficiency", "Proficiency");
        Proficiency second = createProficiency("proficiency", "Proficiency");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<ProficiencyDto> proficiencies = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, proficiencies.getContent().size());
    }

    @Test
    void searchByName_success() {
        Proficiency proficiency = createProficiency("proficiency", "Proficiency");
        ProficiencyDto expected = createProficiencyDto("proficiency", "Proficiency");

        when(repository.findByNameLike(proficiency.getName())).thenReturn(List.of(proficiency));
        when(mapper.toDto(proficiency)).thenReturn(expected);

        SearchResult actual = service.searchByName(proficiency.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
