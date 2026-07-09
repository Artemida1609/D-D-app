package mate.academy.jvteamproject.mapper.impl;

import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.saved.SavedElementRequestDto;
import mate.academy.jvteamproject.dto.saved.SavedElementResponseDto;
import mate.academy.jvteamproject.mapper.SavedElementMapper;
import mate.academy.jvteamproject.model.SavedElement;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class SavedElementMapperImpl implements SavedElementMapper {

    @Override
    public SavedElement toEntity(SavedElementRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        SavedElement savedElement = new SavedElement();

        if ( requestDto.getEntityType() != null ) {
            savedElement.setEntityType( requestDto.getEntityType() );
        }
        if ( requestDto.getEntityId() != null ) {
            savedElement.setEntityId( requestDto.getEntityId() );
        }

        return savedElement;
    }

    @Override
    public SavedElementResponseDto toResponseDto(SavedElement entity) {
        if ( entity == null ) {
            return null;
        }

        SavedElementResponseDto savedElementResponseDto = new SavedElementResponseDto();

        if ( entity.getId() != null ) {
            savedElementResponseDto.setId( entity.getId() );
        }
        if ( entity.getEntityType() != null ) {
            savedElementResponseDto.setEntityType( entity.getEntityType() );
        }
        if ( entity.getEntityId() != null ) {
            savedElementResponseDto.setEntityId( entity.getEntityId() );
        }
        if ( entity.getSavedAt() != null ) {
            savedElementResponseDto.setSavedAt( entity.getSavedAt() );
        }

        return savedElementResponseDto;
    }
}
