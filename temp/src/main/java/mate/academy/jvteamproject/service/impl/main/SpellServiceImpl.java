package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.spell.SpellDto;
import mate.academy.jvteamproject.mapper.main.SpellMapper;
import mate.academy.jvteamproject.repository.main.SpellRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.SpellService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpellServiceImpl implements SpellService, SearchableService {
    private final SpellRepository spellRepository;
    private final SpellMapper spellMapper;

    @Override
    public SpellDto getByOriginalIndex(String index) {
        return spellMapper.toDto(spellRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<SpellDto> getAll(Pageable pageable) {
        return spellRepository.findAll(pageable)
                .map(spellMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return spellRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "spells",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        spellMapper.toDto(e)
                ));
    }
}
