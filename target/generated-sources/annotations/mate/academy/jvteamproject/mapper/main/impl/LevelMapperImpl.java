package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.level.LevelDto;
import mate.academy.jvteamproject.mapper.main.LevelMapper;
import mate.academy.jvteamproject.model.main.Level;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class LevelMapperImpl implements LevelMapper {

    @Override
    public Level toEntity(LevelDto dto) {
        if ( dto == null ) {
            return null;
        }

        Level level = new Level();

        if ( dto.getOriginalIndex() != null ) {
            level.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getLevel() != null ) {
            level.setLevel( dto.getLevel() );
        }
        if ( dto.getAbilityScoreBonuses() != null ) {
            level.setAbilityScoreBonuses( dto.getAbilityScoreBonuses() );
        }
        if ( dto.getProfBonus() != null ) {
            level.setProfBonus( dto.getProfBonus() );
        }
        List<Map<String, Object>> list = dto.getFeatures();
        if ( list != null ) {
            level.setFeatures( new ArrayList<Map<String, Object>>( list ) );
        }
        Map<String, Object> map = dto.getSpellcasting();
        if ( map != null ) {
            level.setSpellcasting( new LinkedHashMap<String, Object>( map ) );
        }
        Map<String, Object> map1 = dto.getClassSpecific();
        if ( map1 != null ) {
            level.setClassSpecific( new LinkedHashMap<String, Object>( map1 ) );
        }
        Map<String, Object> map2 = dto.getClassInfo();
        if ( map2 != null ) {
            level.setClassInfo( new LinkedHashMap<String, Object>( map2 ) );
        }
        if ( dto.getUrl() != null ) {
            level.setUrl( dto.getUrl() );
        }

        fillClassIndex( dto, level );

        return level;
    }

    @Override
    public LevelDto toDto(Level entity) {
        if ( entity == null ) {
            return null;
        }

        LevelDto levelDto = new LevelDto();

        if ( entity.getOriginalIndex() != null ) {
            levelDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getLevel() != null ) {
            levelDto.setLevel( entity.getLevel() );
        }
        if ( entity.getAbilityScoreBonuses() != null ) {
            levelDto.setAbilityScoreBonuses( entity.getAbilityScoreBonuses() );
        }
        if ( entity.getProfBonus() != null ) {
            levelDto.setProfBonus( entity.getProfBonus() );
        }
        List<Map<String, Object>> list = entity.getFeatures();
        if ( list != null ) {
            levelDto.setFeatures( new ArrayList<Map<String, Object>>( list ) );
        }
        Map<String, Object> map = entity.getSpellcasting();
        if ( map != null ) {
            levelDto.setSpellcasting( new LinkedHashMap<String, Object>( map ) );
        }
        Map<String, Object> map1 = entity.getClassSpecific();
        if ( map1 != null ) {
            levelDto.setClassSpecific( new LinkedHashMap<String, Object>( map1 ) );
        }
        Map<String, Object> map2 = entity.getClassInfo();
        if ( map2 != null ) {
            levelDto.setClassInfo( new LinkedHashMap<String, Object>( map2 ) );
        }
        if ( entity.getUrl() != null ) {
            levelDto.setUrl( entity.getUrl() );
        }

        return levelDto;
    }
}
