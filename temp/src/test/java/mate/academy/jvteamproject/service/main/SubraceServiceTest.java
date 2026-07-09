package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.subrace.SubraceDto;
import mate.academy.jvteamproject.mapper.main.SubraceMapper;
import mate.academy.jvteamproject.model.main.Subrace;
import mate.academy.jvteamproject.repository.main.SubraceRepository;
import mate.academy.jvteamproject.service.impl.main.SubraceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSubrace;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSubraceDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubraceServiceTest {
    @InjectMocks
    private SubraceServiceImpl service;
    @Mock
    private SubraceRepository repository;
    @Mock
    private SubraceMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Subrace subrace = createSubrace("subrace", "Subrace");
        SubraceDto expected = createSubraceDto("subrace", "Subrace");

        when(repository.getByOriginalIndex(subrace.getOriginalIndex())).thenReturn(subrace);
        when(mapper.toDto(subrace)).thenReturn(expected);

        SubraceDto actual = service.getByOriginalIndex(subrace.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Subrace first = createSubrace("subrace1", "Subrace1");
        Subrace second = createSubrace("subrace2", "Subrace2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<SubraceDto> subraces = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, subraces.getContent().size());
    }

    @Test
    void searchByName_success() {
        Subrace subrace = createSubrace("subrace", "Subrace");
        SubraceDto expected = createSubraceDto("subrace", "Subrace");

        when(repository.findByNameIgnoreCase(subrace.getName())).thenReturn(Optional.of(subrace));
        when(mapper.toDto(subrace)).thenReturn(expected);

        SearchResult actual = service.searchByName(subrace.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
