package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.damage.DamageTypeDto;
import mate.academy.jvteamproject.mapper.main.DamageTypeMapper;
import mate.academy.jvteamproject.repository.main.DamageTypeRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.DamageTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DamageTypeServiceImpl implements DamageTypeService, SearchableService {
    private final DamageTypeRepository damageTypeRepository;
    private final DamageTypeMapper damageTypeMapper;

    @Override
    public DamageTypeDto getByOriginalIndex(String index) {
        return damageTypeMapper.toDto(damageTypeRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<DamageTypeDto> getAll(Pageable pageable) {
        return damageTypeRepository.findAll(pageable)
                .map(damageTypeMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return damageTypeRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "damage-types",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        damageTypeMapper.toDto(e)
                ));
    }
}
