package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.trait.TraitDto;
import mate.academy.jvteamproject.mapper.main.TraitMapper;
import mate.academy.jvteamproject.repository.main.TraitRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.TraitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraitServiceImpl implements TraitService, SearchableService {
    private final TraitRepository traitRepository;
    private final TraitMapper traitMapper;

    @Override
    public TraitDto getByOriginalIndex(String index) {
        return traitMapper.toDto(traitRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<TraitDto> getAll(Pageable pageable) {
        return traitRepository.findAll(pageable)
                .map(traitMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return traitRepository.findByNameLike(name).stream()
                .findFirst()
                .map(e -> new SearchResult(
                        "traits",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        traitMapper.toDto(e)
                ));
    }
}
