package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.Optional;
import mate.academy.jvteamproject.dto.ability.AbilityScoreDto;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.mapper.main.AbilityScoreMapper;
import mate.academy.jvteamproject.model.main.AbilityScore;
import mate.academy.jvteamproject.repository.main.AbilityScoreRepository;
import mate.academy.jvteamproject.service.impl.main.AbilityScoreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createAbilityScore;
import static mate.academy.jvteamproject.helper.TestDataHelper.createAbilityScoreDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AbilityScoreServiceTest {
    @InjectMocks
    private AbilityScoreServiceImpl service;
    @Mock
    private AbilityScoreRepository repository;
    @Mock
    private AbilityScoreMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        AbilityScore abilityScore = createAbilityScore("ability-score", "Ability Score");
        AbilityScoreDto expected = createAbilityScoreDto("ability-score", "Ability Score");

        when(repository.getByOriginalIndex(abilityScore.getOriginalIndex())).thenReturn(abilityScore);
        when(mapper.toDto(abilityScore)).thenReturn(expected);

        AbilityScoreDto actual = service.getByOriginalIndex(abilityScore.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        AbilityScore first = createAbilityScore("ability-score1", "Ability Score1");

        AbilityScore second = createAbilityScore("ability-score2", "Ability Score2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<AbilityScoreDto> abilityScores = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, abilityScores.getContent().size());
    }

    @Test
    void searchByName_success() {
        AbilityScore abilityScore = createAbilityScore("ability-score", "Ability Score");

        AbilityScoreDto expected = createAbilityScoreDto("ability-score", "Ability Score");

        when(repository.findByNameIgnoreCase(abilityScore.getName())).thenReturn(Optional.of(abilityScore));
        when(mapper.toDto(abilityScore)).thenReturn(expected);

        SearchResult actual = service.searchByName(abilityScore.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
