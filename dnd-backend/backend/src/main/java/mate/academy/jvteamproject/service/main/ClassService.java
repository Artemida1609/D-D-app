package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.dto.level.LevelDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ClassService {
    ClassDto getByOriginalIndex(String index);

    Page<ClassDto> getAll(Pageable pageable);

    LevelDto getLevelByOriginalIndexAndLevel(String index, int level);

    Page<LevelDto> getAllLevelsByIndex(String index, Pageable pageable);

    ResponseEntity<?> uploadImage(String index, MultipartFile file);

    ResponseEntity<byte[]> getImage(String index);

    void deleteImage(String index);
}
