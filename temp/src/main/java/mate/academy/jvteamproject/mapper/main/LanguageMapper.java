package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.language.LanguageDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Language;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface LanguageMapper extends GenericMapper<LanguageDto, Language> {
    Language toEntity(LanguageDto dto);

    LanguageDto toDto(Language entity);
}
