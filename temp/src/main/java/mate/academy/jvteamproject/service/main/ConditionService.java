package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.condition.ConditionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConditionService {
    ConditionDto getByOriginalIndex(String index);

    Page<ConditionDto> getAll(Pageable pageable);
}
