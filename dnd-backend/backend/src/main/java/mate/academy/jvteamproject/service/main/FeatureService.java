package mate.academy.jvteamproject.service.main;

import mate.academy.jvteamproject.dto.feature.FeatureDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeatureService {
    FeatureDto getById(String index);

    Page<FeatureDto> getAll(Pageable pageable);
}
