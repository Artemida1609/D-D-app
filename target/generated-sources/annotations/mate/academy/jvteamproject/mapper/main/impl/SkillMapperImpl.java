package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.skill.SkillDto;
import mate.academy.jvteamproject.mapper.main.SkillMapper;
import mate.academy.jvteamproject.model.main.Skill;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class SkillMapperImpl implements SkillMapper {

    @Override
    public Skill toEntity(SkillDto dto) {
        if ( dto == null ) {
            return null;
        }

        Skill skill = new Skill();

        if ( dto.getOriginalIndex() != null ) {
            skill.setOriginalIndex( dto.getOriginalIndex() );
        }
        if ( dto.getName() != null ) {
            skill.setName( dto.getName() );
        }
        List<String> list = dto.getDescription();
        if ( list != null ) {
            skill.setDescription( new ArrayList<String>( list ) );
        }
        Map<String, Object> map = dto.getAbilityScore();
        if ( map != null ) {
            skill.setAbilityScore( new LinkedHashMap<String, Object>( map ) );
        }
        if ( dto.getUrl() != null ) {
            skill.setUrl( dto.getUrl() );
        }

        return skill;
    }

    @Override
    public SkillDto toDto(Skill entity) {
        if ( entity == null ) {
            return null;
        }

        SkillDto skillDto = new SkillDto();

        if ( entity.getOriginalIndex() != null ) {
            skillDto.setOriginalIndex( entity.getOriginalIndex() );
        }
        if ( entity.getName() != null ) {
            skillDto.setName( entity.getName() );
        }
        List<String> list = entity.getDescription();
        if ( list != null ) {
            skillDto.setDescription( new ArrayList<String>( list ) );
        }
        Map<String, Object> map = entity.getAbilityScore();
        if ( map != null ) {
            skillDto.setAbilityScore( new LinkedHashMap<String, Object>( map ) );
        }
        if ( entity.getUrl() != null ) {
            skillDto.setUrl( entity.getUrl() );
        }

        return skillDto;
    }
}
