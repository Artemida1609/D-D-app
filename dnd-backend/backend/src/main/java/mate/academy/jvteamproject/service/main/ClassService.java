package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.dto.level.LevelDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassService {
    ClassDto getById(String index);

    Page<ClassDto> getAll(Pageable pageable);

    LevelDto getLevelByIdAndLevel(String index, int level);

    Page<LevelDto> getAllLevelsById(String index, Pageable pageable);
}
