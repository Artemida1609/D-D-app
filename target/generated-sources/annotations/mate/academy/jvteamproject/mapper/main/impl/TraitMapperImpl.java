package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.trait.TraitDto;
import mate.academy.jvteamproject.mapper.main.TraitMapper;
import mate.academy.jvteamproject.model.main.Trait;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:43+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class TraitMapperImpl implements TraitMapper {

    @Override
    public Trait toEntity(TraitDto dto) {
        if ( dto == null ) {
            return null;
        }

        Trait trait = new Trait();

        if ( dto.getOriginalIndex() != null ) {
            trait.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            trait.setName( dto.getName() );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            trait.setDescription( new ArrayList<String>( list ) );
        }
        List<Map<String, Object>> list1 = dto.getRaces();
        if ( list1 != null ) {
            trait.setRaces( new ArrayList<Map<String, Object>>( list1 ) );
        }
        List<Map<String, Object>> list2 = dto.getSubraces();
        if ( list2 != null ) {
            trait.setSubraces( new ArrayList<Map<String, Object>>( list2 ) );
        }
        List<Map<String, Object>> list3 = dto.getProficiencies();
        if ( list3 != null ) {
            trait.setProficiencies( new ArrayList<Map<String, Object>>( list3 ) );
        }
        Map<String, Object> map = dto.getTraitSpecific();
        if ( map != null ) {
            trait.setTraitSpecific( new LinkedHashMap<String, Object>( map ) );
        }
        if ( dto.getUrl() != null ) {
            trait.setUrl( dto.getUrl() );
        }

        return trait;
    }

    @Override
    public TraitDto toDto(Trait entity) {
        if ( entity == null ) {
            return null;
        }

        TraitDto traitDto = new TraitDto();

        if ( entity.getOriginalIndex() != null ) {
            traitDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            traitDto.setName( entity.getName() );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            traitDto.setDescription( new ArrayList<String>( list ) );
        }
        List<Map<String, Object>> list1 = entity.getRaces();
        if ( list1 != null ) {
            traitDto.setRaces( new ArrayList<Map<String, Object>>( list1 ) );
        }
        List<Map<String, Object>> list2 = entity.getSubraces();
        if ( list2 != null ) {
            traitDto.setSubraces( new ArrayList<Map<String, Object>>( list2 ) );
        }
        List<Map<String, Object>> list3 = entity.getProficiencies();
        if ( list3 != null ) {
            traitDto.setProficiencies( new ArrayList<Map<String, Object>>( list3 ) );
        }
        Map<String, Object> map = entity.getTraitSpecific();
        if ( map != null ) {
            traitDto.setTraitSpecific( new LinkedHashMap<String, Object>( map ) );
        }
        if ( entity.getUrl() != null ) {
            traitDto.setUrl( entity.getUrl() );
        }

        return traitDto;
    }
}
