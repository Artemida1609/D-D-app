package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.rule.RuleDto;
import mate.academy.jvteamproject.mapper.main.RuleMapper;
import mate.academy.jvteamproject.model.main.Rule;
import mate.academy.jvteamproject.repository.main.RuleRepository;
import mate.academy.jvteamproject.service.impl.main.RuleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createRule;
import static mate.academy.jvteamproject.helper.TestDataHelper.createRuleDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RuleServiceTest {
    @InjectMocks
    private RuleServiceImpl service;
    @Mock
    private RuleRepository repository;
    @Mock
    private RuleMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Rule rule = createRule("rule", "Rule");
        RuleDto expected = createRuleDto("rule", "Rule");

        when(repository.getByOriginalIndex(rule.getOriginalIndex())).thenReturn(rule);
        when(mapper.toDto(rule)).thenReturn(expected);

        RuleDto actual = service.getByOriginalIndex(rule.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Rule first = createRule("rule1", "Rule1");
        Rule second = createRule("rule2", "Rule2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<RuleDto> rules = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, rules.getContent().size());
    }

    @Test
    void searchByName_success() {
        Rule rule = createRule("rule", "Rule");
        RuleDto expected = createRuleDto("rule", "Rule");

        when(repository.findByNameLike(rule.getName())).thenReturn(List.of(rule));
        when(mapper.toDto(rule)).thenReturn(expected);

        SearchResult actual = service.searchByName(rule.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
