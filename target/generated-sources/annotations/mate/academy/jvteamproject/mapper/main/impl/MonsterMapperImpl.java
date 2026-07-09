package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.monster.MonsterDto;
import mate.academy.jvteamproject.mapper.main.MonsterMapper;
import mate.academy.jvteamproject.model.main.Monster;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class MonsterMapperImpl implements MonsterMapper {

    @Override
    public Monster toEntity(MonsterDto dto) {
        if ( dto == null ) {
            return null;
        }

        Monster monster = new Monster();

        if ( dto.getOriginalIndex() != null ) {
            monster.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            monster.setName( dto.getName() );
        }
        if ( dto.getSize() != null ) {
            monster.setSize( dto.getSize() );
        }
        if ( dto.getType() != null ) {
            monster.setType( dto.getType() );
        }
        if ( dto.getAlignment() != null ) {
            monster.setAlignment( dto.getAlignment() );
        }
        if ( dto.getHitPoints() != null ) {
            monster.setHitPoints( dto.getHitPoints() );
        }
        if ( dto.getHitDice() != null ) {
            monster.setHitDice( dto.getHitDice() );
        }
        if ( dto.getStrength() != null ) {
            monster.setStrength( dto.getStrength() );
        }
        if ( dto.getDexterity() != null ) {
            monster.setDexterity( dto.getDexterity() );
        }
        if ( dto.getConstitution() != null ) {
            monster.setConstitution( dto.getConstitution() );
        }
        if ( dto.getIntelligence() != null ) {
            monster.setIntelligence( dto.getIntelligence() );
        }
        if ( dto.getWisdom() != null ) {
            monster.setWisdom( dto.getWisdom() );
        }
        if ( dto.getCharisma() != null ) {
            monster.setCharisma( dto.getCharisma() );
        }
        List<Map<String, Object>> list = dto.getProficiencies();
        if ( list != null ) {
            monster.setProficiencies( new ArrayList<Map<String, Object>>( list ) );
        }
        if ( dto.getLanguages() != null ) {
            monster.setLanguages( dto.getLanguages() );
        }
        if ( dto.getChallengeRating() != null ) {
            monster.setChallengeRating( dto.getChallengeRating() );
        }
        if ( dto.getXp() != null ) {
            monster.setXp( dto.getXp() );
        }
        List<Map<String, Object>> list1 = dto.getActions();
        if ( list1 != null ) {
            monster.setActions( new ArrayList<Map<String, Object>>( list1 ) );
        }
        List<Map<String, Object>> list2 = dto.getSpecialAbilities();
        if ( list2 != null ) {
            monster.setSpecialAbilities( new ArrayList<Map<String, Object>>( list2 ) );
        }
        List<Map<String, Object>> list3 = dto.getLegendaryActions();
        if ( list3 != null ) {
            monster.setLegendaryActions( new ArrayList<Map<String, Object>>( list3 ) );
        }
        List<Map<String, Object>> list4 = dto.getReactions();
        if ( list4 != null ) {
            monster.setReactions( new ArrayList<Map<String, Object>>( list4 ) );
        }
        if ( dto.getImage() != null ) {
            monster.setImage( dto.getImage() );
        }
        if ( dto.getUrl() != null ) {
            monster.setUrl( dto.getUrl() );
        }

        monster.setArmorClass( dto.getArmorClassData() != null && !dto.getArmorClassData().isEmpty() ? (Integer) dto.getArmorClassData().get(0).get("value") : null );
        monster.setWalkSpeed( dto.getSpeed() != null ? (String) dto.getSpeed().get("walk") : null );
        monster.setFlySpeed( dto.getSpeed() != null ? (String) dto.getSpeed().get("fly") : null );
        monster.setSwimSpeed( dto.getSpeed() != null ? (String) dto.getSpeed().get("swim") : null );
        monster.setClimbSpeed( dto.getSpeed() != null ? (String) dto.getSpeed().get("climb") : null );
        monster.setBurrowSpeed( dto.getSpeed() != null ? (String) dto.getSpeed().get("burrow") : null );
        monster.setDarkVision( dto.getSenses() != null ? (String) dto.getSenses().get("darkvision") : null );
        monster.setBlindsight( dto.getSenses() != null ? (String) dto.getSenses().get("blindsight") : null );
        monster.setTremorSense( dto.getSenses() != null ? (String) dto.getSenses().get("tremorsense") : null );
        monster.setTrueSight( dto.getSenses() != null ? (String) dto.getSenses().get("truesight") : null );
        monster.setPassivePerception( dto.getSenses() != null ? (Integer) dto.getSenses().get("passive_perception") : null );

        return monster;
    }

    @Override
    public MonsterDto toDto(Monster entity) {
        if ( entity == null ) {
            return null;
        }

        MonsterDto monsterDto = new MonsterDto();

        if ( entity.getOriginalIndex() != null ) {
            monsterDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            monsterDto.setName( entity.getName() );
        }
        if ( entity.getSize() != null ) {
            monsterDto.setSize( entity.getSize() );
        }
        if ( entity.getType() != null ) {
            monsterDto.setType( entity.getType() );
        }
        if ( entity.getAlignment() != null ) {
            monsterDto.setAlignment( entity.getAlignment() );
        }
        if ( entity.getHitPoints() != null ) {
            monsterDto.setHitPoints( entity.getHitPoints() );
        }
        if ( entity.getHitDice() != null ) {
            monsterDto.setHitDice( entity.getHitDice() );
        }
        if ( entity.getStrength() != null ) {
            monsterDto.setStrength( entity.getStrength() );
        }
        if ( entity.getDexterity() != null ) {
            monsterDto.setDexterity( entity.getDexterity() );
        }
        if ( entity.getConstitution() != null ) {
            monsterDto.setConstitution( entity.getConstitution() );
        }
        if ( entity.getIntelligence() != null ) {
            monsterDto.setIntelligence( entity.getIntelligence() );
        }
        if ( entity.getWisdom() != null ) {
            monsterDto.setWisdom( entity.getWisdom() );
        }
        if ( entity.getCharisma() != null ) {
            monsterDto.setCharisma( entity.getCharisma() );
        }
        List<Map<String, Object>> list = entity.getProficiencies();
        if ( list != null ) {
            monsterDto.setProficiencies( new ArrayList<Map<String, Object>>( list ) );
        }
        if ( entity.getLanguages() != null ) {
            monsterDto.setLanguages( entity.getLanguages() );
        }
        if ( entity.getChallengeRating() != null ) {
            monsterDto.setChallengeRating( entity.getChallengeRating() );
        }
        if ( entity.getXp() != null ) {
            monsterDto.setXp( entity.getXp() );
        }
        List<Map<String, Object>> list1 = entity.getActions();
        if ( list1 != null ) {
            monsterDto.setActions( new ArrayList<Map<String, Object>>( list1 ) );
        }
        List<Map<String, Object>> list2 = entity.getSpecialAbilities();
        if ( list2 != null ) {
            monsterDto.setSpecialAbilities( new ArrayList<Map<String, Object>>( list2 ) );
        }
        List<Map<String, Object>> list3 = entity.getLegendaryActions();
        if ( list3 != null ) {
            monsterDto.setLegendaryActions( new ArrayList<Map<String, Object>>( list3 ) );
        }
        List<Map<String, Object>> list4 = entity.getReactions();
        if ( list4 != null ) {
            monsterDto.setReactions( new ArrayList<Map<String, Object>>( list4 ) );
        }
        if ( entity.getImage() != null ) {
            monsterDto.setImage( entity.getImage() );
        }
        if ( entity.getUrl() != null ) {
            monsterDto.setUrl( entity.getUrl() );
        }

        monsterDto.setArmorClassData( entity.getArmorClass() != null ? java.util.List.of(java.util.Map.of("value", entity.getArmorClass())) : null );
        monsterDto.setSpeed( java.util.stream.Stream.of(entity.getWalkSpeed() != null ? java.util.Map.entry("walk", entity.getWalkSpeed()) : null,entity.getFlySpeed() != null ? java.util.Map.entry("fly", entity.getFlySpeed()) : null, entity.getSwimSpeed() != null ? java.util.Map.entry("swim", entity.getSwimSpeed()) : null,entity.getClimbSpeed() != null ?java.util.Map.entry("climb", entity.getClimbSpeed()) : null,entity.getBurrowSpeed() != null ? java.util.Map.entry("burrow", entity.getBurrowSpeed()) : null).filter(java.util.Objects::nonNull).collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)) );
        monsterDto.setSenses( java.util.stream.Stream.of(entity.getDarkVision() != null ?java.util.Map.entry("darkvision", entity.getDarkVision()) : null,entity.getBlindsight() != null ?java.util.Map.entry("blindsight", entity.getBlindsight()) : null,entity.getTremorSense() != null ?java.util.Map.entry("tremorsense", entity.getTremorSense()) : null,entity.getTrueSight() != null ?java.util.Map.entry("truesight", entity.getTrueSight()) : null,entity.getPassivePerception() != null ?java.util.Map.entry("passive_perception", entity.getPassivePerception()) : null).filter(java.util.Objects::nonNull).collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)) );

        return monsterDto;
    }
}
