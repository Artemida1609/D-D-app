package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.condition.ConditionDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Condition;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ConditionMapper extends GenericMapper<ConditionDto, Condition> {
    Condition toEntity(ConditionDto dto);

    ConditionDto toDto(Condition entity);
}
