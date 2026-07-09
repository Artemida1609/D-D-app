package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.rule.RuleDto;
import mate.academy.jvteamproject.mapper.main.RuleMapper;
import mate.academy.jvteamproject.model.main.Rule;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class RuleMapperImpl implements RuleMapper {

    @Override
    public Rule toEntity(RuleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Rule rule = new Rule();

        if ( dto.getOriginalIndex() != null ) {
            rule.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            rule.setName( dto.getName() );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            rule.setDescription( new ArrayList<String>( list ) );
        }
        if ( dto.getUrl() != null ) {
            rule.setUrl( dto.getUrl() );
        }

        return rule;
    }

    @Override
    public RuleDto toDto(Rule entity) {
        if ( entity == null ) {
            return null;
        }

        RuleDto ruleDto = new RuleDto();

        if ( entity.getOriginalIndex() != null ) {
            ruleDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            ruleDto.setName( entity.getName() );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            ruleDto.setDescription( new ArrayList<String>( list ) );
        }
        if ( entity.getUrl() != null ) {
            ruleDto.setUrl( entity.getUrl() );
        }

        return ruleDto;
    }
}
