package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.proficiency.ProficiencyDto;
import mate.academy.jvteamproject.mapper.main.ProficiencyMapper;
import mate.academy.jvteamproject.repository.main.ProficiencyRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.ProficiencyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProficiencyServiceImpl implements ProficiencyService, SearchableService {
    private final ProficiencyRepository proficiencyRepository;
    private final ProficiencyMapper proficiencyMapper;

    @Override
    public ProficiencyDto getByOriginalIndex(String index) {
        return proficiencyMapper.toDto(proficiencyRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<ProficiencyDto> getAll(Pageable pageable) {
        return proficiencyRepository.findAll(pageable)
                .map(proficiencyMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return proficiencyRepository.findByNameLike(name).stream()
                .findFirst()
                .map(e -> new SearchResult(
                        "proficiencies",
                        e.getName(),
                        e.getNameUa(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        proficiencyMapper.toDto(e)
                ));
    }
}
