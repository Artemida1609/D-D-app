package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.damage.DamageTypeDto;
import mate.academy.jvteamproject.mapper.main.DamageTypeMapper;
import mate.academy.jvteamproject.model.main.DamageType;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class DamageTypeMapperImpl implements DamageTypeMapper {

    @Override
    public DamageType toEntity(DamageTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        DamageType damageType = new DamageType();

        if ( dto.getOriginalIndex() != null ) {
            damageType.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            damageType.setName( dto.getName() );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            damageType.setDescription( new ArrayList<String>( list ) );
        }
        if ( dto.getUrl() != null ) {
            damageType.setUrl( dto.getUrl() );
        }

        return damageType;
    }

    @Override
    public DamageTypeDto toDto(DamageType entity) {
        if ( entity == null ) {
            return null;
        }

        DamageTypeDto damageTypeDto = new DamageTypeDto();

        if ( entity.getOriginalIndex() != null ) {
            damageTypeDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            damageTypeDto.setName( entity.getName() );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            damageTypeDto.setDescription( new ArrayList<String>( list ) );
        }
        if ( entity.getUrl() != null ) {
            damageTypeDto.setUrl( entity.getUrl() );
        }

        return damageTypeDto;
    }
}
