package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.dto.level.LevelDto;
import mate.academy.jvteamproject.mapper.main.ClassMapper;
import mate.academy.jvteamproject.mapper.main.LevelMapper;
import mate.academy.jvteamproject.model.main.Class;
import mate.academy.jvteamproject.model.main.Level;
import mate.academy.jvteamproject.repository.main.ClassRepository;
import mate.academy.jvteamproject.repository.main.LevelRepository;
import mate.academy.jvteamproject.service.impl.main.ClassServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createClass;
import static mate.academy.jvteamproject.helper.TestDataHelper.createClassDto;
import static mate.academy.jvteamproject.helper.TestDataHelper.createLevel;
import static mate.academy.jvteamproject.helper.TestDataHelper.createLevelDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClassServiceTest {
    @InjectMocks
    private ClassServiceImpl service;
    @Mock
    private ClassRepository classRepository;
    @Mock
    private LevelRepository levelRepository;
    @Mock
    private ClassMapper classMapper;
    @Mock
    private LevelMapper levelMapper;

    @Test
    void getByOriginalIndex_success() {
        Class classEntity = createClass("class", "Class");
        ClassDto expected = createClassDto("class", "Class");

        when(classRepository.getByOriginalIndex(classEntity.getOriginalIndex())).thenReturn(classEntity);
        when(classMapper.toDto(classEntity)).thenReturn(expected);

        ClassDto actual = service.getByOriginalIndex(classEntity.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Class first = createClass("class1", "Class1");
        Class second = createClass("class2", "Class2");
        int expectedSize = 2;

        when(classRepository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<ClassDto> classes = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, classes.getContent().size());
    }

    @Test
    void getLevelByOriginalIndexAndLevel_success() {
        Level level = createLevel("level", 1);
        LevelDto expected = createLevelDto("level", 1);

        when(levelRepository.getLevelByOriginalIndex(level.getOriginalIndex(), level.getLevel())).thenReturn(level);
        when(levelMapper.toDto(level)).thenReturn(expected);

        LevelDto actual = service.getLevelByOriginalIndexAndLevel(level.getOriginalIndex(), level.getLevel());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getLevel(), actual.getLevel());
    }

    @Test
    void getAllLevelsByIndex_success() {
        Level first = createLevel("level", 1);
        Level second = createLevel("level", 2);

        int expectedSize = 2;

        when(levelRepository.getAllLevelsByOriginalIndex("level", PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<LevelDto> levels = service.getAllLevelsByIndex("level", PageRequest.of(0, 10));

        assertEquals(expectedSize, levels.getContent().size());
    }

    @Test
    void searchByName_success() {
        Class classEntity = createClass("class", "Class");
        ClassDto expected = createClassDto("class", "Class");

        when(classRepository.findByNameIgnoreCase(classEntity.getName())).thenReturn(Optional.of(classEntity));
        when(classMapper.toDto(classEntity)).thenReturn(expected);

        SearchResult actual = service.searchByName(classEntity.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
