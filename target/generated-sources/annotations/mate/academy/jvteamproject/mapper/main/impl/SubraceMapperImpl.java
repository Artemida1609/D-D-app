package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.subrace.SubraceDto;
import mate.academy.jvteamproject.mapper.main.SubraceMapper;
import mate.academy.jvteamproject.model.main.Subrace;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class SubraceMapperImpl implements SubraceMapper {

    @Override
    public Subrace toEntity(SubraceDto dto) {
        if ( dto == null ) {
            return null;
        }

        Subrace subrace = new Subrace();

        if ( dto.getOriginalIndex() != null ) {
            subrace.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            subrace.setName( dto.getName() );
        }
        Map<String, Object> map = dto.getRace();
        if ( map != null ) {
            subrace.setRace( new LinkedHashMap<String, Object>( map ) );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            subrace.setDescription( new ArrayList<String>( list ) );
        }
        List<Map<String, Object>> list1 = dto.getAbilityBonuses();
        if ( list1 != null ) {
            subrace.setAbilityBonuses( new ArrayList<Map<String, Object>>( list1 ) );
        }
        List<Map<String, Object>> list2 = dto.getRacialTraits();
        if ( list2 != null ) {
            subrace.setRacialTraits( new ArrayList<Map<String, Object>>( list2 ) );
        }
        if ( dto.getUrl() != null ) {
            subrace.setUrl( dto.getUrl() );
        }

        return subrace;
    }

    @Override
    public SubraceDto toDto(Subrace entity) {
        if ( entity == null ) {
            return null;
        }

        SubraceDto subraceDto = new SubraceDto();

        if ( entity.getOriginalIndex() != null ) {
            subraceDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            subraceDto.setName( entity.getName() );
        }
        Map<String, Object> map = entity.getRace();
        if ( map != null ) {
            subraceDto.setRace( new LinkedHashMap<String, Object>( map ) );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            subraceDto.setDescription( new ArrayList<String>( list ) );
        }
        List<Map<String, Object>> list1 = entity.getAbilityBonuses();
        if ( list1 != null ) {
            subraceDto.setAbilityBonuses( new ArrayList<Map<String, Object>>( list1 ) );
        }
        List<Map<String, Object>> list2 = entity.getRacialTraits();
        if ( list2 != null ) {
            subraceDto.setRacialTraits( new ArrayList<Map<String, Object>>( list2 ) );
        }
        if ( entity.getUrl() != null ) {
            subraceDto.setUrl( entity.getUrl() );
        }

        return subraceDto;
    }
}
