package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.api.SearchResult;
import mate.academy.jvteamproject.dto.rule.RuleSectionDto;
import mate.academy.jvteamproject.mapper.main.RuleSectionMapper;
import mate.academy.jvteamproject.repository.main.RuleSectionRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.RuleSectionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleSectionServiceImpl implements RuleSectionService, SearchableService {
    private final RuleSectionRepository ruleSectionRepository;
    private final RuleSectionMapper ruleSectionMapper;

    @Override
    public RuleSectionDto getById(String index) {
        return ruleSectionMapper.toDto(ruleSectionRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<RuleSectionDto> getAll(Pageable pageable) {
        return ruleSectionRepository.findAll(pageable)
                .map(ruleSectionMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return ruleSectionRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "rule-sections",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        ruleSectionMapper.toDto(e)
                ));
    }
}
