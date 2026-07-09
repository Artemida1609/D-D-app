package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.monster.MonsterDto;
import mate.academy.jvteamproject.mapper.main.MonsterMapper;
import mate.academy.jvteamproject.repository.main.MonsterRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.MonsterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonsterServiceImpl implements MonsterService, SearchableService {
    private final MonsterRepository monsterRepository;
    private final MonsterMapper monsterMapper;

    @Override
    public MonsterDto getByOriginalIndex(String index) {
        return monsterMapper.toDto(monsterRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<MonsterDto> getAll(Pageable pageable) {
        return monsterRepository.findAll(pageable)
                .map(monsterMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return monsterRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "monsters",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        monsterMapper.toDto(e)
                ));
    }
}
