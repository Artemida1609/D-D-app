package mate.academy.jvteamproject.service.impl.main;

import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.equipment.EquipmentCategoryDto;
import mate.academy.jvteamproject.mapper.main.EquipmentCategoryMapper;
import mate.academy.jvteamproject.repository.main.EquipmentCategoryRepository;
import mate.academy.jvteamproject.service.main.EquipmentCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentCategoryServiceImpl implements EquipmentCategoryService {
    private final EquipmentCategoryRepository equipmentCategoryRepository;
    private final EquipmentCategoryMapper equipmentCategoryMapper;

    @Override
    public Page<EquipmentCategoryDto> getAllByCategory(String globalCategory, Pageable pageable) {
        return equipmentCategoryRepository.findAllByGlobalCategory(globalCategory, pageable)
                .map(equipmentCategoryMapper::toDto);
    }
}
