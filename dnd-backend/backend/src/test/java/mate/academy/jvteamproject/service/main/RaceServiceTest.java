package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.race.RaceDto;
import mate.academy.jvteamproject.mapper.main.RaceMapper;
import mate.academy.jvteamproject.model.main.Race;
import mate.academy.jvteamproject.repository.main.RaceRepository;
import mate.academy.jvteamproject.service.impl.main.RaceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createRace;
import static mate.academy.jvteamproject.helper.TestDataHelper.createRaceDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RaceServiceTest {
    @InjectMocks
    private RaceServiceImpl service;
    @Mock
    private RaceRepository repository;
    @Mock
    private RaceMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Race race = createRace("race", "Race");
        RaceDto expected = createRaceDto("race", "Race");

        when(repository.getByOriginalIndex(race.getOriginalIndex())).thenReturn(race);
        when(mapper.toDto(race)).thenReturn(expected);

        RaceDto actual = service.getByOriginalIndex(race.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Race first = createRace("race1", "Race1");
        Race second = createRace("race2", "Race2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<RaceDto> races = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, races.getContent().size());
    }

    @Test
    void searchByName_success() {
        Race race = createRace("race", "Race");
        RaceDto expected = createRaceDto("race", "Race");

        when(repository.findByNameIgnoreCase(race.getName())).thenReturn(Optional.of(race));
        when(mapper.toDto(race)).thenReturn(expected);

        SearchResult actual = service.searchByName(race.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
