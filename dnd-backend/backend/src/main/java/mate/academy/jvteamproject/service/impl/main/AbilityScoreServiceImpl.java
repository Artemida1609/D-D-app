package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.ability.AbilityScoreDto;
import mate.academy.jvteamproject.mapper.main.AbilityScoreMapper;
import mate.academy.jvteamproject.repository.main.AbilityScoreRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.AbilityScoreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AbilityScoreServiceImpl implements AbilityScoreService, SearchableService {
    private final AbilityScoreRepository abilityScoreRepository;
    private final AbilityScoreMapper abilityScoreMapper;

    @Override
    public AbilityScoreDto getByOriginalIndex(String index) {
        return abilityScoreMapper.toDto(abilityScoreRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<AbilityScoreDto> getAll(Pageable pageable) {
        return abilityScoreRepository.findAll(pageable)
                .map(abilityScoreMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return abilityScoreRepository.findByNameLike(name).stream()
                .findFirst()
                .map(e -> new SearchResult(
                        "ability-scores",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        abilityScoreMapper.toDto(e)
                ));
    }
}
