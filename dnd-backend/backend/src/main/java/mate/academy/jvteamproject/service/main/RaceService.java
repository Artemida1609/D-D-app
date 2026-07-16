package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.race.RaceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface RaceService {
    RaceDto getByOriginalIndex(String index);

    Page<RaceDto> getAll(Pageable pageable);

    ResponseEntity<?> uploadImage(String index, MultipartFile file);

    ResponseEntity<byte[]> getImage(String index);

    void deleteImage(String index);
}
