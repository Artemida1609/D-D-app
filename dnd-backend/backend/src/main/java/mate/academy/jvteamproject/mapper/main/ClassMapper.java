package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Class;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ClassMapper extends GenericMapper<ClassDto, Class> {
    @Mapping(target = "image", ignore = true)
    Class toEntity(ClassDto dto);

    ClassDto toDto(Class entity);
}
