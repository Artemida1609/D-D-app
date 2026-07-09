package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.subclass.SubclassDto;
import mate.academy.jvteamproject.mapper.main.SubclassMapper;
import mate.academy.jvteamproject.model.main.Subclass;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class SubclassMapperImpl implements SubclassMapper {

    @Override
    public Subclass toEntity(SubclassDto dto) {
        if ( dto == null ) {
            return null;
        }

        Subclass subclass = new Subclass();

        if ( dto.getOriginalIndex() != null ) {
            subclass.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            subclass.setName( dto.getName() );
        }
        if ( dto.getSubclassFlavor() != null ) {
            subclass.setSubclassFlavor( dto.getSubclassFlavor() );
        }
        Map<String, Object> map = dto.getClassRef();
        if ( map != null ) {
            subclass.setClassRef( new LinkedHashMap<String, Object>( map ) );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            subclass.setDescription( new ArrayList<String>( list ) );
        }
        if ( dto.getSubclassLevels() != null ) {
            subclass.setSubclassLevels( dto.getSubclassLevels() );
        }
        if ( dto.getUrl() != null ) {
            subclass.setUrl( dto.getUrl() );
        }

        return subclass;
    }

    @Override
    public SubclassDto toDto(Subclass entity) {
        if ( entity == null ) {
            return null;
        }

        SubclassDto subclassDto = new SubclassDto();

        if ( entity.getOriginalIndex() != null ) {
            subclassDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            subclassDto.setName( entity.getName() );
        }
        if ( entity.getSubclassFlavor() != null ) {
            subclassDto.setSubclassFlavor( entity.getSubclassFlavor() );
        }
        Map<String, Object> map = entity.getClassRef();
        if ( map != null ) {
            subclassDto.setClassRef( new LinkedHashMap<String, Object>( map ) );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            subclassDto.setDescription( new ArrayList<String>( list ) );
        }
        if ( entity.getSubclassLevels() != null ) {
            subclassDto.setSubclassLevels( entity.getSubclassLevels() );
        }
        if ( entity.getUrl() != null ) {
            subclassDto.setUrl( entity.getUrl() );
        }

        return subclassDto;
    }
}
