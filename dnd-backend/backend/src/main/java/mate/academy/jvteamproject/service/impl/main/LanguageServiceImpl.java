package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.api.SearchResult;
import mate.academy.jvteamproject.dto.language.LanguageDto;
import mate.academy.jvteamproject.mapper.main.LanguageMapper;
import mate.academy.jvteamproject.repository.main.LanguageRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.LanguageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService, SearchableService {
    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    @Override
    public LanguageDto getById(String index) {
        return languageMapper.toDto(languageRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<LanguageDto> getAll(Pageable pageable) {
        return languageRepository.findAll(pageable)
                .map(languageMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return languageRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "languages",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        languageMapper.toDto(e)
                ));
    }
}
