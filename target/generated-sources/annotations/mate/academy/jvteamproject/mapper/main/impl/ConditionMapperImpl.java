package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.condition.ConditionDto;
import mate.academy.jvteamproject.mapper.main.ConditionMapper;
import mate.academy.jvteamproject.model.main.Condition;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class ConditionMapperImpl implements ConditionMapper {

    @Override
    public Condition toEntity(ConditionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Condition condition = new Condition();

        if ( dto.getOriginalIndex() != null ) {
            condition.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            condition.setName( dto.getName() );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            condition.setDescription( new ArrayList<String>( list ) );
        }
        if ( dto.getUrl() != null ) {
            condition.setUrl( dto.getUrl() );
        }

        return condition;
    }

    @Override
    public ConditionDto toDto(Condition entity) {
        if ( entity == null ) {
            return null;
        }

        ConditionDto conditionDto = new ConditionDto();

        if ( entity.getOriginalIndex() != null ) {
            conditionDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            conditionDto.setName( entity.getName() );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            conditionDto.setDescription( new ArrayList<String>( list ) );
        }
        if ( entity.getUrl() != null ) {
            conditionDto.setUrl( entity.getUrl() );
        }

        return conditionDto;
    }
}
