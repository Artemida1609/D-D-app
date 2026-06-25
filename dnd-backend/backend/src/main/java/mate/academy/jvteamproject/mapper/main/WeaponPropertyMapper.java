package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.weapon.WeaponPropertyDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.WeaponProperty;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface WeaponPropertyMapper extends GenericMapper<WeaponPropertyDto, WeaponProperty> {
    WeaponProperty toEntity(WeaponPropertyDto dto);

    WeaponPropertyDto toDto(WeaponProperty entity);
}

