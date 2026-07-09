package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.equipment.EquipmentCategoryDto;
import mate.academy.jvteamproject.mapper.main.EquipmentCategoryMapper;
import mate.academy.jvteamproject.model.main.EquipmentCategory;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:43+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class EquipmentCategoryMapperImpl implements EquipmentCategoryMapper {

    @Override
    public EquipmentCategory toEntity(EquipmentCategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        EquipmentCategory equipmentCategory = new EquipmentCategory();

        if ( dto.getOriginalIndex() != null ) {
            equipmentCategory.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getGlobalCategory() != null ) {
            equipmentCategory.setGlobalCategory( dto.getGlobalCategory() );
        }
        if ( dto.getName() != null ) {
            equipmentCategory.setName( dto.getName() );
        }
        List<Map<String, Object>> list = dto.getEquipment();
        if ( list != null ) {
            equipmentCategory.setEquipment( new ArrayList<Map<String, Object>>( list ) );
        }
        if ( dto.getUrl() != null ) {
            equipmentCategory.setUrl( dto.getUrl() );
        }

        return equipmentCategory;
    }

    @Override
    public EquipmentCategoryDto toDto(EquipmentCategory entity) {
        if ( entity == null ) {
            return null;
        }

        EquipmentCategoryDto equipmentCategoryDto = new EquipmentCategoryDto();

        if ( entity.getOriginalIndex() != null ) {
            equipmentCategoryDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getGlobalCategory() != null ) {
            equipmentCategoryDto.setGlobalCategory( entity.getGlobalCategory() );
        }
        if ( entity.getName() != null ) {
            equipmentCategoryDto.setName( entity.getName() );
        }
        List<Map<String, Object>> list = entity.getEquipment();
        if ( list != null ) {
            equipmentCategoryDto.setEquipment( new ArrayList<Map<String, Object>>( list ) );
        }
        if ( entity.getUrl() != null ) {
            equipmentCategoryDto.setUrl( entity.getUrl() );
        }

        return equipmentCategoryDto;
    }
}
