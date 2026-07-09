package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.weapon.WeaponPropertyDto;
import mate.academy.jvteamproject.mapper.main.WeaponPropertyMapper;
import mate.academy.jvteamproject.model.main.WeaponProperty;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class WeaponPropertyMapperImpl implements WeaponPropertyMapper {

    @Override
    public WeaponProperty toEntity(WeaponPropertyDto dto) {
        if ( dto == null ) {
            return null;
        }

        WeaponProperty weaponProperty = new WeaponProperty();

        if ( dto.getOriginalIndex() != null ) {
            weaponProperty.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            weaponProperty.setName( dto.getName() );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            weaponProperty.setDescription( new ArrayList<String>( list ) );
        }
        if ( dto.getUrl() != null ) {
            weaponProperty.setUrl( dto.getUrl() );
        }

        return weaponProperty;
    }

    @Override
    public WeaponPropertyDto toDto(WeaponProperty entity) {
        if ( entity == null ) {
            return null;
        }

        WeaponPropertyDto weaponPropertyDto = new WeaponPropertyDto();

        if ( entity.getOriginalIndex() != null ) {
            weaponPropertyDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            weaponPropertyDto.setName( entity.getName() );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            weaponPropertyDto.setDescription( new ArrayList<String>( list ) );
        }
        if ( entity.getUrl() != null ) {
            weaponPropertyDto.setUrl( entity.getUrl() );
        }

        return weaponPropertyDto;
    }
}
