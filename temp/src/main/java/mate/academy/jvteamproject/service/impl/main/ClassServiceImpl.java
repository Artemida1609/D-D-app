package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.dto.level.LevelDto;
import mate.academy.jvteamproject.mapper.main.ClassMapper;
import mate.academy.jvteamproject.mapper.main.LevelMapper;
import mate.academy.jvteamproject.repository.main.ClassRepository;
import mate.academy.jvteamproject.repository.main.LevelRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.ClassService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService, SearchableService {
    private final ClassRepository classRepository;
    private final ClassMapper classMapper;
    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;

    @Override
    public ClassDto getByOriginalIndex(String index) {
        return classMapper.toDto(classRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<ClassDto> getAll(Pageable pageable) {
        return classRepository.findAll(pageable)
                .map(classMapper::toDto);
    }

    @Override
    public LevelDto getLevelByOriginalIndexAndLevel(String index, int level) {
        return levelMapper.toDto(levelRepository.getLevelByOriginalIndex(index, level));
    }

    @Override
    public Page<LevelDto> getAllLevelsByIndex(String index, Pageable pageable) {
        return levelRepository.getAllLevelsByOriginalIndex(index, pageable)
                .map(levelMapper::toDto);

    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return classRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "classes",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        classMapper.toDto(e)
                ));
    }
}
