package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.rule.RuleDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Rule;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RuleMapper extends GenericMapper<RuleDto, Rule> {
    Rule toEntity(RuleDto dto);

    RuleDto toDto(Rule entity);
}
