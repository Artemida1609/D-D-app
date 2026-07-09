package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.subrace.SubraceDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Subrace;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SubraceMapper extends GenericMapper<SubraceDto, Subrace> {
    Subrace toEntity(SubraceDto dto);

    SubraceDto toDto(Subrace entity);
}
