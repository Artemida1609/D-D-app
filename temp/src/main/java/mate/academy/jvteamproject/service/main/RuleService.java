package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.rule.RuleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RuleService {
    RuleDto getByOriginalIndex(String index);

    Page<RuleDto> getAll(Pageable pageable);
}
