package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.equipment.EquipmentDto;
import mate.academy.jvteamproject.mapper.main.EquipmentMapper;
import mate.academy.jvteamproject.repository.main.EquipmentRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.EquipmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService, SearchableService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    @Override
    public EquipmentDto getByOriginalIndex(String index) {
        return equipmentMapper.toDto(equipmentRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<EquipmentDto> getAll(Pageable pageable) {
        return equipmentRepository.findAll(pageable)
                .map(equipmentMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return equipmentRepository.findByNameLike(name).stream()
                .findFirst()
                .map(e -> new SearchResult(
                        "equipments",
                        e.getName(),
                        e.getNameUa(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        equipmentMapper.toDto(e)
                ));
    }
}
