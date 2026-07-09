package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.rule.RuleSectionDto;
import mate.academy.jvteamproject.mapper.main.RuleSectionMapper;
import mate.academy.jvteamproject.model.main.RuleSection;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class RuleSectionMapperImpl implements RuleSectionMapper {

    @Override
    public RuleSection toEntity(RuleSectionDto dto) {
        if ( dto == null ) {
            return null;
        }

        RuleSection ruleSection = new RuleSection();

        if ( dto.getOriginalIndex() != null ) {
            ruleSection.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            ruleSection.setName( dto.getName() );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            ruleSection.setDescription( new ArrayList<String>( list ) );
        }
        if ( dto.getUrl() != null ) {
            ruleSection.setUrl( dto.getUrl() );
        }

        return ruleSection;
    }

    @Override
    public RuleSectionDto toDto(RuleSection entity) {
        if ( entity == null ) {
            return null;
        }

        RuleSectionDto ruleSectionDto = new RuleSectionDto();

        if ( entity.getOriginalIndex() != null ) {
            ruleSectionDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            ruleSectionDto.setName( entity.getName() );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            ruleSectionDto.setDescription( new ArrayList<String>( list ) );
        }
        if ( entity.getUrl() != null ) {
            ruleSectionDto.setUrl( entity.getUrl() );
        }

        return ruleSectionDto;
    }
}
