package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.mapper.main.ClassMapper;
import mate.academy.jvteamproject.model.main.Class;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class ClassMapperImpl implements ClassMapper {

    @Override
    public Class toEntity(ClassDto dto) {
        if ( dto == null ) {
            return null;
        }

        Class class1 = new Class();

        if ( dto.getOriginalIndex() != null ) {
            class1.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            class1.setName( dto.getName() );
        }
        if ( dto.getHitDie() != null ) {
            class1.setHitDie( dto.getHitDie() );
        }
        List<Map<String, Object>> list = dto.getProficiencyChoices();
        if ( list != null ) {
            class1.setProficiencyChoices( new ArrayList<Map<String, Object>>( list ) );
        }
        List<Map<String, Object>> list1 = dto.getProficiencies();
        if ( list1 != null ) {
            class1.setProficiencies( new ArrayList<Map<String, Object>>( list1 ) );
        }
        List<Map<String, Object>> list2 = dto.getSavingThrows();
        if ( list2 != null ) {
            class1.setSavingThrows( new ArrayList<Map<String, Object>>( list2 ) );
        }
        List<Map<String, Object>> list3 = dto.getStartingEquipment();
        if ( list3 != null ) {
            class1.setStartingEquipment( new ArrayList<Map<String, Object>>( list3 ) );
        }
        if ( dto.getClassLevels() != null ) {
            class1.setClassLevels( dto.getClassLevels() );
        }
        Map<String, Object> map = dto.getMultiClassing();
        if ( map != null ) {
            class1.setMultiClassing( new LinkedHashMap<String, Object>( map ) );
        }
        List<Map<String, Object>> list4 = dto.getSubclasses();
        if ( list4 != null ) {
            class1.setSubclasses( new ArrayList<Map<String, Object>>( list4 ) );
        }
        if ( dto.getUrl() != null ) {
            class1.setUrl( dto.getUrl() );
        }

        return class1;
    }

    @Override
    public ClassDto toDto(Class entity) {
        if ( entity == null ) {
            return null;
        }

        ClassDto classDto = new ClassDto();

        if ( entity.getOriginalIndex() != null ) {
            classDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            classDto.setName( entity.getName() );
        }
        if ( entity.getHitDie() != null ) {
            classDto.setHitDie( entity.getHitDie() );
        }
        List<Map<String, Object>> list = entity.getProficiencyChoices();
        if ( list != null ) {
            classDto.setProficiencyChoices( new ArrayList<Map<String, Object>>( list ) );
        }
        List<Map<String, Object>> list1 = entity.getProficiencies();
        if ( list1 != null ) {
            classDto.setProficiencies( new ArrayList<Map<String, Object>>( list1 ) );
        }
        List<Map<String, Object>> list2 = entity.getSavingThrows();
        if ( list2 != null ) {
            classDto.setSavingThrows( new ArrayList<Map<String, Object>>( list2 ) );
        }
        List<Map<String, Object>> list3 = entity.getStartingEquipment();
        if ( list3 != null ) {
            classDto.setStartingEquipment( new ArrayList<Map<String, Object>>( list3 ) );
        }
        if ( entity.getClassLevels() != null ) {
            classDto.setClassLevels( entity.getClassLevels() );
        }
        Map<String, Object> map = entity.getMultiClassing();
        if ( map != null ) {
            classDto.setMultiClassing( new LinkedHashMap<String, Object>( map ) );
        }
        List<Map<String, Object>> list4 = entity.getSubclasses();
        if ( list4 != null ) {
            classDto.setSubclasses( new ArrayList<Map<String, Object>>( list4 ) );
        }
        if ( entity.getUrl() != null ) {
            classDto.setUrl( entity.getUrl() );
        }

        return classDto;
    }
}
