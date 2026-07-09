package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.school.MagicSchoolDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MagicSchoolService {
    MagicSchoolDto getByOriginalIndex(String index);

    Page<MagicSchoolDto> getAll(Pageable pageable);
}
