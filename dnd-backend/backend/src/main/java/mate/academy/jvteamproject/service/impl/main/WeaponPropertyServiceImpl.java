package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.api.SearchResult;
import mate.academy.jvteamproject.dto.weapon.WeaponPropertyDto;
import mate.academy.jvteamproject.mapper.main.WeaponPropertyMapper;
import mate.academy.jvteamproject.repository.main.WeaponPropertyRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.WeaponPropertyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeaponPropertyServiceImpl implements WeaponPropertyService, SearchableService {
    private final WeaponPropertyRepository weaponPropertyRepository;
    private final WeaponPropertyMapper weaponPropertyMapper;

    @Override
    public WeaponPropertyDto getById(String index) {
        return weaponPropertyMapper.toDto(weaponPropertyRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<WeaponPropertyDto> getAll(Pageable pageable) {
        return weaponPropertyRepository.findAll(pageable)
                .map(weaponPropertyMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return weaponPropertyRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "weapon-properties",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        weaponPropertyMapper.toDto(e)
                ));
    }
}
