package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.condition.ConditionDto;
import mate.academy.jvteamproject.mapper.main.ConditionMapper;
import mate.academy.jvteamproject.repository.main.ConditionRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.ConditionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConditionServiceImpl implements ConditionService, SearchableService {
    private final ConditionRepository conditionRepository;
    private final ConditionMapper conditionMapper;

    @Override
    public ConditionDto getByOriginalIndex(String index) {
        return conditionMapper.toDto(conditionRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<ConditionDto> getAll(Pageable pageable) {
        return conditionRepository.findAll(pageable)
                .map(conditionMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return conditionRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "conditions",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        conditionMapper.toDto(e)
                ));
    }
}
