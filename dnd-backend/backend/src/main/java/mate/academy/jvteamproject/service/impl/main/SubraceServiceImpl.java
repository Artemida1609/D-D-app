package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.api.SearchResult;
import mate.academy.jvteamproject.dto.subrace.SubraceDto;
import mate.academy.jvteamproject.mapper.main.SubraceMapper;
import mate.academy.jvteamproject.repository.main.SubraceRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.SubraceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubraceServiceImpl implements SubraceService, SearchableService {
    private final SubraceRepository subraceRepository;
    private final SubraceMapper subraceMapper;

    @Override
    public SubraceDto getById(String index) {
        return subraceMapper.toDto(subraceRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<SubraceDto> getAll(Pageable pageable) {
        return subraceRepository.findAll(pageable)
                .map(subraceMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return subraceRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "subraces",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        subraceMapper.toDto(e)
                ));
    }
}
