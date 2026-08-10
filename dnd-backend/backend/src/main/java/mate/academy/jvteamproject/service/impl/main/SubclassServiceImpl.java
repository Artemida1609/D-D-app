package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.subclass.SubclassDto;
import mate.academy.jvteamproject.mapper.main.SubclassMapper;
import mate.academy.jvteamproject.repository.main.SubclassRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.SubclassService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubclassServiceImpl implements SubclassService, SearchableService {
    private final SubclassRepository subclassRepository;
    private final SubclassMapper subclassMapper;

    @Override
    public SubclassDto getByOriginalIndex(String index) {
        return subclassMapper.toDto(subclassRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<SubclassDto> getAll(Pageable pageable) {
        return subclassRepository.findAll(pageable)
                .map(subclassMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return subclassRepository.findByNameLike(name).stream()
                .findFirst()
                .map(e -> new SearchResult(
                        "subclasses",
                        e.getName(),
                        e.getNameUa(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        subclassMapper.toDto(e)
                ));
    }
}
