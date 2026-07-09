package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.ability.AbilityScoreDto;
import mate.academy.jvteamproject.mapper.main.AbilityScoreMapper;
import mate.academy.jvteamproject.model.main.AbilityScore;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class AbilityScoreMapperImpl implements AbilityScoreMapper {

    @Override
    public AbilityScoreDto toDto(AbilityScore entity) {
        if ( entity == null ) {
            return null;
        }

        AbilityScoreDto abilityScoreDto = new AbilityScoreDto();

        if ( entity.getOriginalIndex() != null ) {
            abilityScoreDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            abilityScoreDto.setName( entity.getName() );
        }
        if ( entity.getFullName() != null ) {
            abilityScoreDto.setFullName( entity.getFullName() );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            abilityScoreDto.setDescription( new ArrayList<String>( list ) );
        }
        List<Map<String, Object>> list1 = entity.getSkills();
        if ( list1 != null ) {
            abilityScoreDto.setSkills( new ArrayList<Map<String, Object>>( list1 ) );
        }
        if ( entity.getUrl() != null ) {
            abilityScoreDto.setUrl( entity.getUrl() );
        }

        return abilityScoreDto;
    }

    @Override
    public AbilityScore toEntity(AbilityScoreDto dto) {
        if ( dto == null ) {
            return null;
        }

        AbilityScore abilityScore = new AbilityScore();

        if ( dto.getOriginalIndex() != null ) {
            abilityScore.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            abilityScore.setName( dto.getName() );
        }
        if ( dto.getFullName() != null ) {
            abilityScore.setFullName( dto.getFullName() );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            abilityScore.setDescription( new ArrayList<String>( list ) );
        }
        List<Map<String, Object>> list1 = dto.getSkills();
        if ( list1 != null ) {
            abilityScore.setSkills( new ArrayList<Map<String, Object>>( list1 ) );
        }
        if ( dto.getUrl() != null ) {
            abilityScore.setUrl( dto.getUrl() );
        }

        return abilityScore;
    }
}
