package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.race.RaceDto;
import mate.academy.jvteamproject.mapper.main.RaceMapper;
import mate.academy.jvteamproject.model.main.Race;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class RaceMapperImpl implements RaceMapper {

    @Override
    public Race toEntity(RaceDto dto) {
        if ( dto == null ) {
            return null;
        }

        Race race = new Race();

        List<Map<String, Object>> list = dto.getStartingProficiencies();
        if ( list != null ) {
            race.setStartingProficiencies( new ArrayList<Map<String, Object>>( list ) );
        }
        if ( dto.getOriginalIndex() != null ) {
            race.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            race.setName( dto.getName() );
        }
        if ( dto.getSpeed() != null ) {
            race.setSpeed( dto.getSpeed() );
        }
        if ( dto.getAlignment() != null ) {
            race.setAlignment( dto.getAlignment() );
        }
        if ( dto.getAge() != null ) {
            race.setAge( dto.getAge() );
        }
        if ( dto.getSize() != null ) {
            race.setSize( dto.getSize() );
        }
        if ( dto.getSizeDescription() != null ) {
            race.setSizeDescription( dto.getSizeDescription() );
        }
        List<Map<String, Object>> list1 = dto.getLanguages();
        if ( list1 != null ) {
            race.setLanguages( new ArrayList<Map<String, Object>>( list1 ) );
        }
        if ( dto.getLanguageDesc() != null ) {
            race.setLanguageDesc( dto.getLanguageDesc() );
        }
        List<Map<String, Object>> list2 = dto.getTraits();
        if ( list2 != null ) {
            race.setTraits( new ArrayList<Map<String, Object>>( list2 ) );
        }
        List<Map<String, Object>> list3 = dto.getSubraces();
        if ( list3 != null ) {
            race.setSubraces( new ArrayList<Map<String, Object>>( list3 ) );
        }
        if ( dto.getUrl() != null ) {
            race.setUrl( dto.getUrl() );
        }

        race.setAbilityBonusAbilityScore( dto.getAbilityBonuses() != null ? dto.getAbilityBonuses().stream().map(b -> (Map<String,Object>) b.get("ability_score")).toList() : null );
        race.setAbilityBonusValue( dto.getAbilityBonuses() != null ? dto.getAbilityBonuses().stream().map(b -> (Integer) b.get("bonus")).toList() : null );

        return race;
    }

    @Override
    public RaceDto toDto(Race entity) {
        if ( entity == null ) {
            return null;
        }

        RaceDto raceDto = new RaceDto();

        if ( entity.getOriginalIndex() != null ) {
            raceDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            raceDto.setName( entity.getName() );
        }
        if ( entity.getSpeed() != null ) {
            raceDto.setSpeed( entity.getSpeed() );
        }
        if ( entity.getAlignment() != null ) {
            raceDto.setAlignment( entity.getAlignment() );
        }
        if ( entity.getAge() != null ) {
            raceDto.setAge( entity.getAge() );
        }
        if ( entity.getSize() != null ) {
            raceDto.setSize( entity.getSize() );
        }
        if ( entity.getSizeDescription() != null ) {
            raceDto.setSizeDescription( entity.getSizeDescription() );
        }
        List<Map<String, Object>> list = entity.getStartingProficiencies();
        if ( list != null ) {
            raceDto.setStartingProficiencies( new ArrayList<Map<String, Object>>( list ) );
        }
        List<Map<String, Object>> list1 = entity.getLanguages();
        if ( list1 != null ) {
            raceDto.setLanguages( new ArrayList<Map<String, Object>>( list1 ) );
        }
        if ( entity.getLanguageDesc() != null ) {
            raceDto.setLanguageDesc( entity.getLanguageDesc() );
        }
        List<Map<String, Object>> list2 = entity.getTraits();
        if ( list2 != null ) {
            raceDto.setTraits( new ArrayList<Map<String, Object>>( list2 ) );
        }
        List<Map<String, Object>> list3 = entity.getSubraces();
        if ( list3 != null ) {
            raceDto.setSubraces( new ArrayList<Map<String, Object>>( list3 ) );
        }
        if ( entity.getUrl() != null ) {
            raceDto.setUrl( entity.getUrl() );
        }

        raceDto.setAbilityBonuses( entity.getAbilityBonusAbilityScore() != null && entity.getAbilityBonusValue() != null ? java.util.stream.IntStream.range(0, entity.getAbilityBonusAbilityScore().size()).mapToObj(i -> java.util.Map.of("ability_score", entity.getAbilityBonusAbilityScore().get(i),"bonus", entity.getAbilityBonusValue().get(i))).toList() : null );

        return raceDto;
    }
}
