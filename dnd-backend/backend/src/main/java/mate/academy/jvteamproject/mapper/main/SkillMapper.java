package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.skill.SkillDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Skill;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SkillMapper extends GenericMapper<SkillDto, Skill> {
    Skill toEntity(SkillDto dto);

    SkillDto toDto(Skill entity);
}
