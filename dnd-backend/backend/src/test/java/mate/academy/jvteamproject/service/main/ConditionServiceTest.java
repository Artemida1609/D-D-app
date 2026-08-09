package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.condition.ConditionDto;
import mate.academy.jvteamproject.mapper.main.ConditionMapper;
import mate.academy.jvteamproject.model.main.Condition;
import mate.academy.jvteamproject.repository.main.ConditionRepository;
import mate.academy.jvteamproject.service.impl.main.ConditionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createCondition;
import static mate.academy.jvteamproject.helper.TestDataHelper.createConditionDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConditionServiceTest {
    @InjectMocks
    private ConditionServiceImpl service;
    @Mock
    private ConditionRepository repository;
    @Mock
    private ConditionMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Condition condition = createCondition("condition", "Condition");
        ConditionDto expected = createConditionDto("condition", "Condition");

        when(repository.getByOriginalIndex(condition.getOriginalIndex())).thenReturn(condition);
        when(mapper.toDto(condition)).thenReturn(expected);

        ConditionDto actual = service.getByOriginalIndex(condition.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Condition first = createCondition("condition1", "Condition1");
        Condition second = createCondition("condition2", "Condition2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<ConditionDto> conditions = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, conditions.getContent().size());
    }

    @Test
    void searchByName_success() {
        Condition condition = createCondition("condition", "Condition");
        ConditionDto expected = createConditionDto("condition", "Condition");

        when(repository.findByNameLike(condition.getName())).thenReturn(List.of(condition));
        when(mapper.toDto(condition)).thenReturn(expected);

        SearchResult actual = service.searchByName(condition.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
