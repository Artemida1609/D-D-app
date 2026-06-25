package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.equipment.EquipmentCategoryDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.EquipmentCategory;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface EquipmentCategoryMapper extends
        GenericMapper<EquipmentCategoryDto, EquipmentCategory> {

    EquipmentCategory toEntity(EquipmentCategoryDto dto);

    EquipmentCategoryDto toDto(EquipmentCategory entity);
}
