package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.rule.RuleSectionDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.RuleSection;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RuleSectionMapper extends GenericMapper<RuleSectionDto, RuleSection> {
    RuleSection toEntity(RuleSectionDto dto);

    RuleSectionDto toDto(RuleSection entity);
}
