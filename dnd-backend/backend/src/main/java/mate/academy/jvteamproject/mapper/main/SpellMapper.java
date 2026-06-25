package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.spell.SpellDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Spell;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface SpellMapper extends GenericMapper<SpellDto, Spell> {
    @Mapping(target = "damageType",
            expression = "java(dto.getDamage() != null ? (Map<String,Object>) "
                    + "dto.getDamage().get(\"damage_type\") : null)")

    @Mapping(target = "damageAtSlotLevel",
            expression = "java(dto.getDamage() != null ? (Map<String,Object>) "
                    + "dto.getDamage().get(\"damage_at_slot_level\") : null)")

    @Mapping(target = "dcType",
            expression = "java(dto.getDc() != null ? (Map<String,Object>) "
                    + "dto.getDc().get(\"dc_type\") : null)")

    @Mapping(target = "dcSuccess",
            expression = "java(dto.getDc() != null ? (String) "
                    + "dto.getDc().get(\"dc_success\") : null)")

    @Mapping(target = "areaOfEffectType",
            expression = "java(dto.getAreaOfEffect() != null ? (String) "
                    + "dto.getAreaOfEffect().get(\"type\") : null)")

    @Mapping(target = "areaOfEffectSize",
            expression = "java(dto.getAreaOfEffect() != null ? (Integer) "
                    + "dto.getAreaOfEffect().get(\"size\") : null)")
    Spell toEntity(SpellDto dto);

    @Mapping(target = "damage",
            expression = "java(java.util.stream.Stream.of("
            + "entity.getDamageType() != null ?"
            + " java.util.Map.entry(\"damage_type\", entity.getDamageType()) : null,"
            + "entity.getDamageAtSlotLevel() != null ? "
            + "java.util.Map.entry(\"damage_at_slot_level\", entity.getDamageAtSlotLevel()) : null)"
            + ".filter(java.util.Objects::nonNull)"
            + ".collect(java.util.stream.Collectors"
            + ".toMap(Map.Entry::getKey, Map.Entry::getValue)))")
    @Mapping(target = "dc",
            expression = "java(java.util.stream.Stream.of("
            + "entity.getDcType() != null ?"
            + " java.util.Map.entry(\"dc_type\", entity.getDcType()) : null,"
            + "entity.getDcSuccess() != null ?"
            + " java.util.Map.entry(\"dc_success\", entity.getDcSuccess()) : null)"
            + ".filter(java.util.Objects::nonNull)"
            + ".collect(java.util.stream.Collectors"
            + ".toMap(Map.Entry::getKey, Map.Entry::getValue)))")
    @Mapping(target = "areaOfEffect",
            expression = "java(java.util.stream.Stream.of("
            + "entity.getAreaOfEffectType() != null ?"
            + "java.util.Map.entry(\"type\", entity.getAreaOfEffectType()) : null,"
            + "entity.getAreaOfEffectSize() != null ?"
            + "java.util.Map.entry(\"size\", entity.getAreaOfEffectSize()) : null)"
            + ".filter(java.util.Objects::nonNull)"
            + ".collect(java.util.stream.Collectors"
            + ".toMap(Map.Entry::getKey, Map.Entry::getValue)))")
    SpellDto toDto(Spell entity);
}
