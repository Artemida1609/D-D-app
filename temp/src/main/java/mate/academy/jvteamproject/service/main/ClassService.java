package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.dto.level.LevelDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassService {
    ClassDto getByOriginalIndex(String index);

    Page<ClassDto> getAll(Pageable pageable);

    LevelDto getLevelByOriginalIndexAndLevel(String index, int level);

    Page<LevelDto> getAllLevelsByIndex(String index, Pageable pageable);
}
