package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.subclass.SubclassDto;
import mate.academy.jvteamproject.mapper.main.SubclassMapper;
import mate.academy.jvteamproject.model.main.Subclass;
import mate.academy.jvteamproject.repository.main.SubclassRepository;
import mate.academy.jvteamproject.service.impl.main.SubclassServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSubclass;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSubclassDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubclassServiceTest {
    @InjectMocks
    private SubclassServiceImpl service;
    @Mock
    private SubclassRepository repository;
    @Mock
    private SubclassMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Subclass subclass = createSubclass("subclass", "Subclass");
        SubclassDto expected = createSubclassDto("subclass", "Subclass");

        when(repository.getByOriginalIndex(subclass.getOriginalIndex())).thenReturn(subclass);
        when(mapper.toDto(subclass)).thenReturn(expected);

        SubclassDto actual = service.getByOriginalIndex(subclass.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Subclass first = createSubclass("subclass1", "Subclass1");
        Subclass second = createSubclass("subclass2", "Subclass2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<SubclassDto> subclasses = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, subclasses.getContent().size());
    }

    @Test
    void searchByName_success() {
        Subclass subclass = createSubclass("subclass", "Subclass");
        SubclassDto expected = createSubclassDto("subclass", "Subclass");

        when(repository.findByNameLike(subclass.getName())).thenReturn(List.of(subclass));
        when(mapper.toDto(subclass)).thenReturn(expected);

        SearchResult actual = service.searchByName(subclass.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
