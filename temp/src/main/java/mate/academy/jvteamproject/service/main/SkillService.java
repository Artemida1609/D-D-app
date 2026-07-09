package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.skill.SkillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SkillService {
    SkillDto getByOriginalIndex(String index);

    Page<SkillDto> getAll(Pageable pageable);
}
