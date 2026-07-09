package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.feature.FeatureDto;
import mate.academy.jvteamproject.mapper.main.FeatureMapper;
import mate.academy.jvteamproject.model.main.Feature;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class FeatureMapperImpl implements FeatureMapper {

    @Override
    public Feature toEntity(FeatureDto dto) {
        if ( dto == null ) {
            return null;
        }

        Feature feature = new Feature();

        if ( dto.getOriginalIndex() != null ) {
            feature.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            feature.setName( dto.getName() );
        }
        if ( dto.getLevel() != null ) {
            feature.setLevel( dto.getLevel() );
        }
        Map<String, Object> map = dto.getClassRef();
        if ( map != null ) {
            feature.setClassRef( new LinkedHashMap<String, Object>( map ) );
        }
        Map<String, Object> map1 = dto.getSubclassRef();
        if ( map1 != null ) {
            feature.setSubclassRef( new LinkedHashMap<String, Object>( map1 ) );
        }
        List<Map<String, Object>> list = dto.getPrerequisites();
        if ( list != null ) {
            feature.setPrerequisites( new ArrayList<Map<String, Object>>( list ) );
        }
        List<String> list1 = dto.getDescription();
        if ( list1 != null ) {
            feature.setDescription( new ArrayList<String>( list1 ) );
        }
        Map<String, Object> map2 = dto.getFeatureSpecific();
        if ( map2 != null ) {
            feature.setFeatureSpecific( new LinkedHashMap<String, Object>( map2 ) );
        }
        if ( dto.getUrl() != null ) {
            feature.setUrl( dto.getUrl() );
        }

        return feature;
    }

    @Override
    public FeatureDto toDto(Feature entity) {
        if ( entity == null ) {
            return null;
        }

        FeatureDto featureDto = new FeatureDto();

        if ( entity.getOriginalIndex() != null ) {
            featureDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            featureDto.setName( entity.getName() );
        }
        if ( entity.getLevel() != null ) {
            featureDto.setLevel( entity.getLevel() );
        }
        Map<String, Object> map = entity.getClassRef();
        if ( map != null ) {
            featureDto.setClassRef( new LinkedHashMap<String, Object>( map ) );
        }
        Map<String, Object> map1 = entity.getSubclassRef();
        if ( map1 != null ) {
            featureDto.setSubclassRef( new LinkedHashMap<String, Object>( map1 ) );
        }
        List<Map<String, Object>> list = entity.getPrerequisites();
        if ( list != null ) {
            featureDto.setPrerequisites( new ArrayList<Map<String, Object>>( list ) );
        }
        List<String> list1 = entity.getDescription();
        if ( list1 != null ) {
            featureDto.setDescription( new ArrayList<String>( list1 ) );
        }
        Map<String, Object> map2 = entity.getFeatureSpecific();
        if ( map2 != null ) {
            featureDto.setFeatureSpecific( new LinkedHashMap<String, Object>( map2 ) );
        }
        if ( entity.getUrl() != null ) {
            featureDto.setUrl( entity.getUrl() );
        }

        return featureDto;
    }
}
