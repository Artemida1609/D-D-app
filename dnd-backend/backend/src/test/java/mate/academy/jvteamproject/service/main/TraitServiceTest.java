package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.trait.TraitDto;
import mate.academy.jvteamproject.mapper.main.TraitMapper;
import mate.academy.jvteamproject.model.main.Trait;
import mate.academy.jvteamproject.repository.main.TraitRepository;
import mate.academy.jvteamproject.service.impl.main.TraitServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createTrait;
import static mate.academy.jvteamproject.helper.TestDataHelper.createTraitDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TraitServiceTest {
    @InjectMocks
    private TraitServiceImpl service;
    @Mock
    private TraitRepository repository;
    @Mock
    private TraitMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Trait trait = createTrait("trait", "Trait");
        TraitDto expected = createTraitDto("trait", "Trait");

        when(repository.getByOriginalIndex(trait.getOriginalIndex())).thenReturn(trait);
        when(mapper.toDto(trait)).thenReturn(expected);

        TraitDto actual = service.getByOriginalIndex(trait.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Trait first = createTrait("trait1", "Trait1");
        Trait second = createTrait("trait2", "Trait2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<TraitDto> traits = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, traits.getContent().size());
    }

    @Test
    void searchByName_success() {
        Trait trait = createTrait("trait", "Trait");
        TraitDto expected = createTraitDto("trait", "Trait");

        when(repository.findByNameLike(trait.getName())).thenReturn(List.of(trait));
        when(mapper.toDto(trait)).thenReturn(expected);

        SearchResult actual = service.searchByName(trait.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
