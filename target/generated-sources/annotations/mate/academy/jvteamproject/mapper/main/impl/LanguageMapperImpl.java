package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.language.LanguageDto;
import mate.academy.jvteamproject.mapper.main.LanguageMapper;
import mate.academy.jvteamproject.model.main.Language;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class LanguageMapperImpl implements LanguageMapper {

    @Override
    public Language toEntity(LanguageDto dto) {
        if ( dto == null ) {
            return null;
        }

        Language language = new Language();

        if ( dto.getOriginalIndex() != null ) {
            language.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            language.setName( dto.getName() );
        }
        if ( dto.getType() != null ) {
            language.setType( dto.getType() );
        }
        if ( dto.getScript() != null ) {
            language.setScript( dto.getScript() );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            language.setDescription( new ArrayList<String>( list ) );
        }
        List<String> list1 = dto.getTypicalSpeakers();
        if ( list1 != null ) {
            language.setTypicalSpeakers( new ArrayList<String>( list1 ) );
        }
        if ( dto.getUrl() != null ) {
            language.setUrl( dto.getUrl() );
        }

        return language;
    }

    @Override
    public LanguageDto toDto(Language entity) {
        if ( entity == null ) {
            return null;
        }

        LanguageDto languageDto = new LanguageDto();

        if ( entity.getOriginalIndex() != null ) {
            languageDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            languageDto.setName( entity.getName() );
        }
        if ( entity.getType() != null ) {
            languageDto.setType( entity.getType() );
        }
        if ( entity.getScript() != null ) {
            languageDto.setScript( entity.getScript() );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            languageDto.setDescription( new ArrayList<String>( list ) );
        }
        List<String> list1 = entity.getTypicalSpeakers();
        if ( list1 != null ) {
            languageDto.setTypicalSpeakers( new ArrayList<String>( list1 ) );
        }
        if ( entity.getUrl() != null ) {
            languageDto.setUrl( entity.getUrl() );
        }

        return languageDto;
    }
}
