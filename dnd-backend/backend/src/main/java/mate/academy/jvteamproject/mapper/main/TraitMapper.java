package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.trait.TraitDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Trait;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface TraitMapper extends GenericMapper<TraitDto, Trait> {
    Trait toEntity(TraitDto dto);

    TraitDto toDto(Trait entity);
}
