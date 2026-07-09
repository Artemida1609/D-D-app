package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.subclass.SubclassDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Subclass;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SubclassMapper extends GenericMapper<SubclassDto, Subclass> {
    Subclass toEntity(SubclassDto dto);

    SubclassDto toDto(Subclass entity);
}
