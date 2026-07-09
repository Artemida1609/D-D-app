package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.rule.RuleSectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RuleSectionService {
    RuleSectionDto getByOriginalIndex(String index);

    Page<RuleSectionDto> getAll(Pageable pageable);
}
