package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.rule.RuleSectionDto;
import mate.academy.jvteamproject.mapper.main.RuleSectionMapper;
import mate.academy.jvteamproject.model.main.RuleSection;
import mate.academy.jvteamproject.repository.main.RuleSectionRepository;
import mate.academy.jvteamproject.service.impl.main.RuleSectionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createRuleSection;
import static mate.academy.jvteamproject.helper.TestDataHelper.createRuleSectionDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RuleSectionServiceTest {
    @InjectMocks
    private RuleSectionServiceImpl service;
    @Mock
    private RuleSectionRepository repository;
    @Mock
    private RuleSectionMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        RuleSection race = createRuleSection("rule-section", "Rule Section");
        RuleSectionDto expected = createRuleSectionDto("rule-section", "Rule Section");

        when(repository.getByOriginalIndex(race.getOriginalIndex())).thenReturn(race);
        when(mapper.toDto(race)).thenReturn(expected);

        RuleSectionDto actual = service.getByOriginalIndex(race.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        RuleSection first = createRuleSection("rule-section1", "Rule Section1");
        RuleSection second = createRuleSection("rule-section2", "Rule Section2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<RuleSectionDto> ruleSections = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, ruleSections.getContent().size());
    }

    @Test
    void searchByName_success() {
        RuleSection race = createRuleSection("rule-section", "Rule Section");
        RuleSectionDto expected = createRuleSectionDto("rule-section", "Rule Section");

        when(repository.findByNameLike(race.getName())).thenReturn(List.of(race));
        when(mapper.toDto(race)).thenReturn(expected);

        SearchResult actual = service.searchByName(race.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
