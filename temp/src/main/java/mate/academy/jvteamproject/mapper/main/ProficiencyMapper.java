package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.proficiency.ProficiencyDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Proficiency;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ProficiencyMapper extends GenericMapper<ProficiencyDto, Proficiency> {
    Proficiency toEntity(ProficiencyDto dto);

    ProficiencyDto toDto(Proficiency entity);
}
