package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.monster.MonsterDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Monster;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface MonsterMapper extends GenericMapper<MonsterDto, Monster> {
    @Mapping(target = "armorClass",
            expression = "java(dto.getArmorClassData() != null "
                    + "&& !dto.getArmorClassData().isEmpty() ? (Integer) "
                    + "dto.getArmorClassData().get(0).get(\"value\") : null)")

    @Mapping(target = "walkSpeed",
            expression = "java(dto.getSpeed() != null ? (String) "
                    + "dto.getSpeed().get(\"walk\") : null)")

    @Mapping(target = "flySpeed",
            expression = "java(dto.getSpeed() != null ? (String) "
                    + "dto.getSpeed().get(\"fly\") : null)")

    @Mapping(target = "swimSpeed",
            expression = "java(dto.getSpeed() != null ? (String) "
                    + "dto.getSpeed().get(\"swim\") : null)")

    @Mapping(target = "climbSpeed",
            expression = "java(dto.getSpeed() != null ? (String) "
                    + "dto.getSpeed().get(\"climb\") : null)")

    @Mapping(target = "burrowSpeed",
            expression = "java(dto.getSpeed() != null ? (String) "
                    + "dto.getSpeed().get(\"burrow\") : null)")

    @Mapping(target = "darkVision",
            expression = "java(dto.getSenses() != null ? (String) "
                    + "dto.getSenses().get(\"darkvision\") : null)")

    @Mapping(target = "blindsight",
            expression = "java(dto.getSenses() != null ? (String) "
                    + "dto.getSenses().get(\"blindsight\") : null)")

    @Mapping(target = "tremorSense",
            expression = "java(dto.getSenses() != null ? (String) "
                    + "dto.getSenses().get(\"tremorsense\") : null)")

    @Mapping(target = "trueSight",
            expression = "java(dto.getSenses() != null ? (String) "
                    + "dto.getSenses().get(\"truesight\") : null)")

    @Mapping(target = "passivePerception",
            expression = "java(dto.getSenses() != null ? (Integer) "
                    + "dto.getSenses().get(\"passive_perception\") : null)")
    Monster toEntity(MonsterDto dto);

    @Mapping(target = "armorClassData",
            expression = "java(entity.getArmorClass() != null ? "
                    + "java.util.List.of(java.util.Map.of(\"value\", entity.getArmorClass())) "
                    + ": null)")

    @Mapping(target = "speed",
            expression = "java(java.util.stream.Stream.of("
            + "entity.getWalkSpeed() != null ? "
            + "java.util.Map.entry(\"walk\", entity.getWalkSpeed()) : null,"
            + "entity.getFlySpeed() != null ? "
            + "java.util.Map.entry(\"fly\", entity.getFlySpeed()) : null, "
            + "entity.getSwimSpeed() != null ? "
            + "java.util.Map.entry(\"swim\", entity.getSwimSpeed()) : null,"
            + "entity.getClimbSpeed() != null ?"
            + "java.util.Map.entry(\"climb\", entity.getClimbSpeed()) : null,"
            + "entity.getBurrowSpeed() != null ? "
            + "java.util.Map.entry(\"burrow\", entity.getBurrowSpeed()) : null)"
            + ".filter(java.util.Objects::nonNull)"
            + ".collect(java.util.stream.Collectors"
            + ".toMap(Map.Entry::getKey, Map.Entry::getValue)))")

    @Mapping(target = "senses",
            expression = "java(java.util.stream.Stream.of("
            + "entity.getDarkVision() != null ?"
            + "java.util.Map.entry(\"darkvision\", entity.getDarkVision()) : null,"
            + "entity.getBlindsight() != null ?"
            + "java.util.Map.entry(\"blindsight\", entity.getBlindsight()) : null,"
            + "entity.getTremorSense() != null ?"
            + "java.util.Map.entry(\"tremorsense\", entity.getTremorSense()) : null,"
            + "entity.getTrueSight() != null ?"
            + "java.util.Map.entry(\"truesight\", entity.getTrueSight()) : null,"
            + "entity.getPassivePerception() != null ?"
            + "java.util.Map.entry(\"passive_perception\", entity.getPassivePerception()) : null)"
            + ".filter(java.util.Objects::nonNull)"
            + ".collect(java.util.stream.Collectors"
            + ".toMap(Map.Entry::getKey, Map.Entry::getValue)))")
    MonsterDto toDto(Monster entity);
}
