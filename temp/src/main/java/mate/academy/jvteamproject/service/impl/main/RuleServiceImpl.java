package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.rule.RuleDto;
import mate.academy.jvteamproject.mapper.main.RuleMapper;
import mate.academy.jvteamproject.repository.main.RuleRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.RuleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService, SearchableService {
    private final RuleRepository ruleRepository;
    private final RuleMapper ruleMapper;

    @Override
    public RuleDto getByOriginalIndex(String index) {
        return ruleMapper.toDto(ruleRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<RuleDto> getAll(Pageable pageable) {
        return ruleRepository.findAll(pageable)
                .map(ruleMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return ruleRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "rules",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        ruleMapper.toDto(e)
                ));
    }
}
