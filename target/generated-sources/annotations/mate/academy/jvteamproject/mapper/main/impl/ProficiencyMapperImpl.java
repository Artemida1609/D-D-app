package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.proficiency.ProficiencyDto;
import mate.academy.jvteamproject.mapper.main.ProficiencyMapper;
import mate.academy.jvteamproject.model.main.Proficiency;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class ProficiencyMapperImpl implements ProficiencyMapper {

    @Override
    public Proficiency toEntity(ProficiencyDto dto) {
        if ( dto == null ) {
            return null;
        }

        Proficiency proficiency = new Proficiency();

        if ( dto.getOriginalIndex() != null ) {
            proficiency.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            proficiency.setName( dto.getName() );
        }
        if ( dto.getType() != null ) {
            proficiency.setType( dto.getType() );
        }
        List<Map<String, Object>> list = dto.getClasses();
        if ( list != null ) {
            proficiency.setClasses( new ArrayList<Map<String, Object>>( list ) );
        }
        List<Map<String, Object>> list1 = dto.getRaces();
        if ( list1 != null ) {
            proficiency.setRaces( new ArrayList<Map<String, Object>>( list1 ) );
        }
        if ( dto.getUrl() != null ) {
            proficiency.setUrl( dto.getUrl() );
        }

        return proficiency;
    }

    @Override
    public ProficiencyDto toDto(Proficiency entity) {
        if ( entity == null ) {
            return null;
        }

        ProficiencyDto proficiencyDto = new ProficiencyDto();

        if ( entity.getOriginalIndex() != null ) {
            proficiencyDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            proficiencyDto.setName( entity.getName() );
        }
        if ( entity.getType() != null ) {
            proficiencyDto.setType( entity.getType() );
        }
        List<Map<String, Object>> list = entity.getClasses();
        if ( list != null ) {
            proficiencyDto.setClasses( new ArrayList<Map<String, Object>>( list ) );
        }
        List<Map<String, Object>> list1 = entity.getRaces();
        if ( list1 != null ) {
            proficiencyDto.setRaces( new ArrayList<Map<String, Object>>( list1 ) );
        }
        if ( entity.getUrl() != null ) {
            proficiencyDto.setUrl( entity.getUrl() );
        }

        return proficiencyDto;
    }
}
