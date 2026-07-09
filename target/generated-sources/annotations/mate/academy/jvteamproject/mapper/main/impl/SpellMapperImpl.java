package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.spell.SpellDto;
import mate.academy.jvteamproject.mapper.main.SpellMapper;
import mate.academy.jvteamproject.model.main.Spell;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class SpellMapperImpl implements SpellMapper {

    @Override
    public Spell toEntity(SpellDto dto) {
        if ( dto == null ) {
            return null;
        }

        Spell spell = new Spell();

        if ( dto.getOriginalIndex() != null ) {
            spell.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            spell.setName( dto.getName() );
        }
        if ( dto.getLevel() != null ) {
            spell.setLevel( dto.getLevel() );
        }
        Map<String, Object> map = dto.getSchool();
        if ( map != null ) {
            spell.setSchool( new LinkedHashMap<String, Object>( map ) );
        }
        List<Map<String, Object>> list = dto.getClasses();
        if ( list != null ) {
            spell.setClasses( new ArrayList<Map<String, Object>>( list ) );
        }
        List<Map<String, Object>> list1 = dto.getSubclasses();
        if ( list1 != null ) {
            spell.setSubclasses( new ArrayList<Map<String, Object>>( list1 ) );
        }
        List<String> list2 = dto.getDescription();
        if ( list2 != null ) {
            spell.setDescription( new ArrayList<String>( list2 ) );
        }
        List<String> list3 = dto.getHigherLevel();
        if ( list3 != null ) {
            spell.setHigherLevel( new ArrayList<String>( list3 ) );
        }
        if ( dto.getRange() != null ) {
            spell.setRange( dto.getRange() );
        }
        List<String> list4 = dto.getComponents();
        if ( list4 != null ) {
            spell.setComponents( new ArrayList<String>( list4 ) );
        }
        if ( dto.getMaterial() != null ) {
            spell.setMaterial( dto.getMaterial() );
        }
        if ( dto.getRitual() != null ) {
            spell.setRitual( dto.getRitual() );
        }
        if ( dto.getDuration() != null ) {
            spell.setDuration( dto.getDuration() );
        }
        if ( dto.getConcentration() != null ) {
            spell.setConcentration( dto.getConcentration() );
        }
        if ( dto.getCastingTime() != null ) {
            spell.setCastingTime( dto.getCastingTime() );
        }
        if ( dto.getAttackType() != null ) {
            spell.setAttackType( dto.getAttackType() );
        }
        if ( dto.getUrl() != null ) {
            spell.setUrl( dto.getUrl() );
        }

        spell.setDamageType( dto.getDamage() != null ? (Map<String,Object>) dto.getDamage().get("damage_type") : null );
        spell.setDamageAtSlotLevel( dto.getDamage() != null ? (Map<String,Object>) dto.getDamage().get("damage_at_slot_level") : null );
        spell.setDcType( dto.getDc() != null ? (Map<String,Object>) dto.getDc().get("dc_type") : null );
        spell.setDcSuccess( dto.getDc() != null ? (String) dto.getDc().get("dc_success") : null );
        spell.setAreaOfEffectType( dto.getAreaOfEffect() != null ? (String) dto.getAreaOfEffect().get("type") : null );
        spell.setAreaOfEffectSize( dto.getAreaOfEffect() != null ? (Integer) dto.getAreaOfEffect().get("size") : null );

        return spell;
    }

    @Override
    public SpellDto toDto(Spell entity) {
        if ( entity == null ) {
            return null;
        }

        SpellDto spellDto = new SpellDto();

        if ( entity.getOriginalIndex() != null ) {
            spellDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            spellDto.setName( entity.getName() );
        }
        if ( entity.getLevel() != null ) {
            spellDto.setLevel( entity.getLevel() );
        }
        Map<String, Object> map = entity.getSchool();
        if ( map != null ) {
            spellDto.setSchool( new LinkedHashMap<String, Object>( map ) );
        }
        List<Map<String, Object>> list = entity.getClasses();
        if ( list != null ) {
            spellDto.setClasses( new ArrayList<Map<String, Object>>( list ) );
        }
        List<Map<String, Object>> list1 = entity.getSubclasses();
        if ( list1 != null ) {
            spellDto.setSubclasses( new ArrayList<Map<String, Object>>( list1 ) );
        }
        List<String> list2 = entity.getDescription();
        if ( list2 != null ) {
            spellDto.setDescription( new ArrayList<String>( list2 ) );
        }
        List<String> list3 = entity.getHigherLevel();
        if ( list3 != null ) {
            spellDto.setHigherLevel( new ArrayList<String>( list3 ) );
        }
        if ( entity.getRange() != null ) {
            spellDto.setRange( entity.getRange() );
        }
        List<String> list4 = entity.getComponents();
        if ( list4 != null ) {
            spellDto.setComponents( new ArrayList<String>( list4 ) );
        }
        if ( entity.getMaterial() != null ) {
            spellDto.setMaterial( entity.getMaterial() );
        }
        if ( entity.getRitual() != null ) {
            spellDto.setRitual( entity.getRitual() );
        }
        if ( entity.getDuration() != null ) {
            spellDto.setDuration( entity.getDuration() );
        }
        if ( entity.getConcentration() != null ) {
            spellDto.setConcentration( entity.getConcentration() );
        }
        if ( entity.getCastingTime() != null ) {
            spellDto.setCastingTime( entity.getCastingTime() );
        }
        if ( entity.getAttackType() != null ) {
            spellDto.setAttackType( entity.getAttackType() );
        }
        if ( entity.getUrl() != null ) {
            spellDto.setUrl( entity.getUrl() );
        }

        spellDto.setDamage( java.util.stream.Stream.of(entity.getDamageType() != null ? java.util.Map.entry("damage_type", entity.getDamageType()) : null,entity.getDamageAtSlotLevel() != null ? java.util.Map.entry("damage_at_slot_level", entity.getDamageAtSlotLevel()) : null).filter(java.util.Objects::nonNull).collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)) );
        spellDto.setDc( java.util.stream.Stream.of(entity.getDcType() != null ? java.util.Map.entry("dc_type", entity.getDcType()) : null,entity.getDcSuccess() != null ? java.util.Map.entry("dc_success", entity.getDcSuccess()) : null).filter(java.util.Objects::nonNull).collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)) );
        spellDto.setAreaOfEffect( java.util.stream.Stream.of(entity.getAreaOfEffectType() != null ?java.util.Map.entry("type", entity.getAreaOfEffectType()) : null,entity.getAreaOfEffectSize() != null ?java.util.Map.entry("size", entity.getAreaOfEffectSize()) : null).filter(java.util.Objects::nonNull).collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)) );

        return spellDto;
    }
}
