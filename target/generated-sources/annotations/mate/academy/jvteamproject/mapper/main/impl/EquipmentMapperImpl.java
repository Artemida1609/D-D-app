package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.equipment.EquipmentDto;
import mate.academy.jvteamproject.mapper.main.EquipmentMapper;
import mate.academy.jvteamproject.model.main.Equipment;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class EquipmentMapperImpl implements EquipmentMapper {

    @Override
    public Equipment toEntity(EquipmentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Equipment equipment = new Equipment();

        if ( dto.getOriginalIndex() != null ) {
            equipment.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            equipment.setName( dto.getName() );
        }
        Map<String, Object> map = dto.getEquipmentCategory();
        if ( map != null ) {
            equipment.setEquipmentCategory( new LinkedHashMap<String, Object>( map ) );
        }
        Map<String, Object> map1 = dto.getGearCategory();
        if ( map1 != null ) {
            equipment.setGearCategory( new LinkedHashMap<String, Object>( map1 ) );
        }
        Map<String, Object> map2 = dto.getCost();
        if ( map2 != null ) {
            equipment.setCost( new LinkedHashMap<String, Object>( map2 ) );
        }
        if ( dto.getWeight() != null ) {
            equipment.setWeight( dto.getWeight() );
        }
        if ( dto.getArmorCategory() != null ) {
            equipment.setArmorCategory( dto.getArmorCategory() );
        }
        Map<String, Object> map3 = dto.getArmorClass();
        if ( map3 != null ) {
            equipment.setArmorClass( new LinkedHashMap<String, Object>( map3 ) );
        }
        if ( dto.getCapacity() != null ) {
            equipment.setCapacity( dto.getCapacity() );
        }
        if ( dto.getCategoryRange() != null ) {
            equipment.setCategoryRange( dto.getCategoryRange() );
        }
        List<Map<String, Object>> list = dto.getContents();
        if ( list != null ) {
            equipment.setContents( new ArrayList<Map<String, Object>>( list ) );
        }
        Map<String, Object> map4 = dto.getDamage();
        if ( map4 != null ) {
            equipment.setDamage( new LinkedHashMap<String, Object>( map4 ) );
        }
        if ( dto.getImage() != null ) {
            equipment.setImage( dto.getImage() );
        }
        List<Map<String, Object>> list1 = dto.getProperties();
        if ( list1 != null ) {
            equipment.setProperties( new ArrayList<Map<String, Object>>( list1 ) );
        }
        if ( dto.getQuantity() != null ) {
            equipment.setQuantity( dto.getQuantity() );
        }
        Map<String, Object> map5 = dto.getRange();
        if ( map5 != null ) {
            equipment.setRange( new LinkedHashMap<String, Object>( map5 ) );
        }
        List<String> list2 = dto.getSpecial();
        if ( list2 != null ) {
            equipment.setSpecial( new ArrayList<String>( list2 ) );
        }
        Map<String, Object> map6 = dto.getSpeed();
        if ( map6 != null ) {
            equipment.setSpeed( new LinkedHashMap<String, Object>( map6 ) );
        }
        if ( dto.getStealthDisadvantage() != null ) {
            equipment.setStealthDisadvantage( dto.getStealthDisadvantage() );
        }
        if ( dto.getStrMinimum() != null ) {
            equipment.setStrMinimum( dto.getStrMinimum() );
        }
        Map<String, Object> map7 = dto.getThrowRange();
        if ( map7 != null ) {
            equipment.setThrowRange( new LinkedHashMap<String, Object>( map7 ) );
        }
        if ( dto.getToolCategory() != null ) {
            equipment.setToolCategory( dto.getToolCategory() );
        }
        Map<String, Object> map8 = dto.getTwoHandedDamage();
        if ( map8 != null ) {
            equipment.setTwoHandedDamage( new LinkedHashMap<String, Object>( map8 ) );
        }
        if ( dto.getVehicleCategory() != null ) {
            equipment.setVehicleCategory( dto.getVehicleCategory() );
        }
        if ( dto.getWeaponCategory() != null ) {
            equipment.setWeaponCategory( dto.getWeaponCategory() );
        }
        if ( dto.getWeaponRange() != null ) {
            equipment.setWeaponRange( dto.getWeaponRange() );
        }
        if ( dto.getUrl() != null ) {
            equipment.setUrl( dto.getUrl() );
        }

        return equipment;
    }

    @Override
    public EquipmentDto toDto(Equipment entity) {
        if ( entity == null ) {
            return null;
        }

        EquipmentDto equipmentDto = new EquipmentDto();

        if ( entity.getOriginalIndex() != null ) {
            equipmentDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            equipmentDto.setName( entity.getName() );
        }
        Map<String, Object> map = entity.getEquipmentCategory();
        if ( map != null ) {
            equipmentDto.setEquipmentCategory( new LinkedHashMap<String, Object>( map ) );
        }
        Map<String, Object> map1 = entity.getGearCategory();
        if ( map1 != null ) {
            equipmentDto.setGearCategory( new LinkedHashMap<String, Object>( map1 ) );
        }
        Map<String, Object> map2 = entity.getCost();
        if ( map2 != null ) {
            equipmentDto.setCost( new LinkedHashMap<String, Object>( map2 ) );
        }
        if ( entity.getWeight() != null ) {
            equipmentDto.setWeight( entity.getWeight() );
        }
        if ( entity.getArmorCategory() != null ) {
            equipmentDto.setArmorCategory( entity.getArmorCategory() );
        }
        Map<String, Object> map3 = entity.getArmorClass();
        if ( map3 != null ) {
            equipmentDto.setArmorClass( new LinkedHashMap<String, Object>( map3 ) );
        }
        if ( entity.getCapacity() != null ) {
            equipmentDto.setCapacity( entity.getCapacity() );
        }
        if ( entity.getCategoryRange() != null ) {
            equipmentDto.setCategoryRange( entity.getCategoryRange() );
        }
        List<Map<String, Object>> list = entity.getContents();
        if ( list != null ) {
            equipmentDto.setContents( new ArrayList<Map<String, Object>>( list ) );
        }
        Map<String, Object> map4 = entity.getDamage();
        if ( map4 != null ) {
            equipmentDto.setDamage( new LinkedHashMap<String, Object>( map4 ) );
        }
        if ( entity.getImage() != null ) {
            equipmentDto.setImage( entity.getImage() );
        }
        List<Map<String, Object>> list1 = entity.getProperties();
        if ( list1 != null ) {
            equipmentDto.setProperties( new ArrayList<Map<String, Object>>( list1 ) );
        }
        if ( entity.getQuantity() != null ) {
            equipmentDto.setQuantity( entity.getQuantity() );
        }
        Map<String, Object> map5 = entity.getRange();
        if ( map5 != null ) {
            equipmentDto.setRange( new LinkedHashMap<String, Object>( map5 ) );
        }
        List<String> list2 = entity.getSpecial();
        if ( list2 != null ) {
            equipmentDto.setSpecial( new ArrayList<String>( list2 ) );
        }
        Map<String, Object> map6 = entity.getSpeed();
        if ( map6 != null ) {
            equipmentDto.setSpeed( new LinkedHashMap<String, Object>( map6 ) );
        }
        if ( entity.getStealthDisadvantage() != null ) {
            equipmentDto.setStealthDisadvantage( entity.getStealthDisadvantage() );
        }
        if ( entity.getStrMinimum() != null ) {
            equipmentDto.setStrMinimum( entity.getStrMinimum() );
        }
        Map<String, Object> map7 = entity.getThrowRange();
        if ( map7 != null ) {
            equipmentDto.setThrowRange( new LinkedHashMap<String, Object>( map7 ) );
        }
        if ( entity.getToolCategory() != null ) {
            equipmentDto.setToolCategory( entity.getToolCategory() );
        }
        Map<String, Object> map8 = entity.getTwoHandedDamage();
        if ( map8 != null ) {
            equipmentDto.setTwoHandedDamage( new LinkedHashMap<String, Object>( map8 ) );
        }
        if ( entity.getVehicleCategory() != null ) {
            equipmentDto.setVehicleCategory( entity.getVehicleCategory() );
        }
        if ( entity.getWeaponCategory() != null ) {
            equipmentDto.setWeaponCategory( entity.getWeaponCategory() );
        }
        if ( entity.getWeaponRange() != null ) {
            equipmentDto.setWeaponRange( entity.getWeaponRange() );
        }
        if ( entity.getUrl() != null ) {
            equipmentDto.setUrl( entity.getUrl() );
        }

        return equipmentDto;
    }
}
