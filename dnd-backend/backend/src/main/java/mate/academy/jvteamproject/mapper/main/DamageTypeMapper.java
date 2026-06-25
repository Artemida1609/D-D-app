package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.damage.DamageTypeDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.DamageType;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface DamageTypeMapper extends GenericMapper<DamageTypeDto, DamageType> {
    DamageType toEntity(DamageTypeDto dto);

    DamageTypeDto toDto(DamageType entity);
}
