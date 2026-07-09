package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.equipment.EquipmentDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Equipment;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface EquipmentMapper extends GenericMapper<EquipmentDto, Equipment> {
    Equipment toEntity(EquipmentDto dto);

    EquipmentDto toDto(Equipment entity);
}

