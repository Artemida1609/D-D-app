package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.ability.AbilityScoreDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.AbilityScore;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface AbilityScoreMapper extends GenericMapper<AbilityScoreDto, AbilityScore> {
    AbilityScoreDto toDto(AbilityScore entity);

    AbilityScore toEntity(AbilityScoreDto dto);
}
