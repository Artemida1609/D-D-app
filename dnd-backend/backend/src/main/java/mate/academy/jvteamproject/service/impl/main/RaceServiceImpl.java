package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.api.SearchResult;
import mate.academy.jvteamproject.dto.race.RaceDto;
import mate.academy.jvteamproject.mapper.main.RaceMapper;
import mate.academy.jvteamproject.repository.main.RaceRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.RaceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaceServiceImpl implements RaceService, SearchableService {
    private final RaceRepository raceRepository;
    private final RaceMapper raceMapper;

    @Override
    public RaceDto getById(String index) {
        return raceMapper.toDto(raceRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<RaceDto> getAll(Pageable pageable) {
        return raceRepository.findAll(pageable)
                .map(raceMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return raceRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "races",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        raceMapper.toDto(e)
                ));
    }
}
