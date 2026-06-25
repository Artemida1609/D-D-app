package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.school.MagicSchoolDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.MagicSchool;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface MagicSchoolMapper extends GenericMapper<MagicSchoolDto, MagicSchool> {
    MagicSchool toEntity(MagicSchoolDto dto);

    MagicSchoolDto toDto(MagicSchool entity);
}
