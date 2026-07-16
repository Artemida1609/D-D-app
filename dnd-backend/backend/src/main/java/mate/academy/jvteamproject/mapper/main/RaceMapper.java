package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.race.RaceDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Race;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface RaceMapper extends GenericMapper<RaceDto, Race> {
    @Mapping(target = "abilityBonusAbilityScore",
            expression = "java(dto.getAbilityBonuses() != null ? "
                    + "dto.getAbilityBonuses().stream().map(b -> (Map<String,Object>) "
                    + "b.get(\"ability_score\")).toList() : null)")

    @Mapping(target = "abilityBonusValue",
            expression = "java(dto.getAbilityBonuses() != null ? "
                    + "dto.getAbilityBonuses().stream().map(b -> (Integer) "
                    + "b.get(\"bonus\")).toList() : null)")
    @Mapping(target = "startingProficiencies", source = "startingProficiencies")
    @Mapping(target = "image", ignore = true)
    Race toEntity(RaceDto dto);

    @Mapping(target = "abilityBonuses",
            expression = "java(entity.getAbilityBonusAbilityScore() != null "
                    + "&& entity.getAbilityBonusValue() != null ? "
                    + "java.util.stream.IntStream.range(0, entity.getAbilityBonusAbilityScore()"
                    + ".size()).mapToObj(i -> java.util.Map.of("
                    + "\"ability_score\", entity.getAbilityBonusAbilityScore().get(i),"
                    + "\"bonus\", entity.getAbilityBonusValue().get(i))).toList() : null)")
    RaceDto toDto(Race entity);
}
