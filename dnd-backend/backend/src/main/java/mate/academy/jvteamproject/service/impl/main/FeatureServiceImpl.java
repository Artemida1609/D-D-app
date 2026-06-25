package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.api.SearchResult;
import mate.academy.jvteamproject.dto.feature.FeatureDto;
import mate.academy.jvteamproject.mapper.main.FeatureMapper;
import mate.academy.jvteamproject.repository.main.FeatureRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.FeatureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService, SearchableService {
    private final FeatureRepository featureRepository;
    private final FeatureMapper featureMapper;

    @Override
    public FeatureDto getById(String index) {
        return featureMapper.toDto(featureRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<FeatureDto> getAll(Pageable pageable) {
        return featureRepository.findAll(pageable)
                .map(featureMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return featureRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "features",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        featureMapper.toDto(e)
                ));
    }
}
