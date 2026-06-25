package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.api.SearchResult;
import mate.academy.jvteamproject.dto.skill.SkillDto;
import mate.academy.jvteamproject.mapper.main.SkillMapper;
import mate.academy.jvteamproject.repository.main.SkillRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.SkillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService, SearchableService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    @Override
    public SkillDto getById(String index) {
        return skillMapper.toDto(skillRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<SkillDto> getAll(Pageable pageable) {
        return skillRepository.findAll(pageable)
                .map(skillMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return skillRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "skills",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        skillMapper.toDto(e)
                ));
    }
}
