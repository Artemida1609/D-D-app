package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.skill.SkillDto;
import mate.academy.jvteamproject.mapper.main.SkillMapper;
import mate.academy.jvteamproject.model.main.Skill;
import mate.academy.jvteamproject.repository.main.SkillRepository;
import mate.academy.jvteamproject.service.impl.main.SkillServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSkill;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSkillDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {
    @InjectMocks
    private SkillServiceImpl service;
    @Mock
    private SkillRepository repository;
    @Mock
    private SkillMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Skill rule = createSkill("skill", "Skill");
        SkillDto expected = createSkillDto("skill", "Skill");

        when(repository.getByOriginalIndex(rule.getOriginalIndex())).thenReturn(rule);
        when(mapper.toDto(rule)).thenReturn(expected);

        SkillDto actual = service.getByOriginalIndex(rule.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Skill first = createSkill("skill1", "Skill1");
        Skill second = createSkill("skill2", "Skill2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<SkillDto> skills = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, skills.getContent().size());
    }

    @Test
    void searchByName_success() {
        Skill skill = createSkill("skill", "Skill");
        SkillDto expected = createSkillDto("skill", "Skill");

        when(repository.findByNameIgnoreCase(skill.getName())).thenReturn(Optional.of(skill));
        when(mapper.toDto(skill)).thenReturn(expected);

        SearchResult actual = service.searchByName(skill.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
