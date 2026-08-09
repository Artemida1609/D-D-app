package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.monster.MonsterDto;
import mate.academy.jvteamproject.mapper.main.MonsterMapper;
import mate.academy.jvteamproject.model.main.Monster;
import mate.academy.jvteamproject.repository.main.MonsterRepository;
import mate.academy.jvteamproject.service.impl.main.MonsterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createMonster;
import static mate.academy.jvteamproject.helper.TestDataHelper.createMonsterDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MonsterServiceTest {
    @InjectMocks
    private MonsterServiceImpl service;
    @Mock
    private MonsterRepository repository;
    @Mock
    private MonsterMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Monster monster = createMonster("monster", "Monster");
        MonsterDto expected = createMonsterDto("monster", "Monster");

        when(repository.getByOriginalIndex(monster.getOriginalIndex())).thenReturn(monster);
        when(mapper.toDto(monster)).thenReturn(expected);

        MonsterDto actual = service.getByOriginalIndex(monster.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Monster first = createMonster("monster1", "Monster1");
        Monster second = createMonster("monster2", "Monster2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<MonsterDto> monsters = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, monsters.getContent().size());
    }

    @Test
    void searchByName_success() {
        Monster monster = createMonster("monster", "Monster");
        MonsterDto expected = createMonsterDto("monster", "Monster");

        when(repository.findByNameLike(monster.getName())).thenReturn(List.of(monster));
        when(mapper.toDto(monster)).thenReturn(expected);

        SearchResult actual = service.searchByName(monster.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
