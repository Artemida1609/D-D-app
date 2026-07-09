package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.school.MagicSchoolDto;
import mate.academy.jvteamproject.mapper.main.MagicSchoolMapper;
import mate.academy.jvteamproject.model.main.MagicSchool;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class MagicSchoolMapperImpl implements MagicSchoolMapper {

    @Override
    public MagicSchool toEntity(MagicSchoolDto dto) {
        if ( dto == null ) {
            return null;
        }

        MagicSchool magicSchool = new MagicSchool();

        if ( dto.getOriginalIndex() != null ) {
            magicSchool.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            magicSchool.setName( dto.getName() );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            magicSchool.setDescription( new ArrayList<String>( list ) );
        }
        if ( dto.getUrl() != null ) {
            magicSchool.setUrl( dto.getUrl() );
        }

        return magicSchool;
    }

    @Override
    public MagicSchoolDto toDto(MagicSchool entity) {
        if ( entity == null ) {
            return null;
        }

        MagicSchoolDto magicSchoolDto = new MagicSchoolDto();

        if ( entity.getOriginalIndex() != null ) {
            magicSchoolDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            magicSchoolDto.setName( entity.getName() );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            magicSchoolDto.setDescription( new ArrayList<String>( list ) );
        }
        if ( entity.getUrl() != null ) {
            magicSchoolDto.setUrl( entity.getUrl() );
        }

        return magicSchoolDto;
    }
}
