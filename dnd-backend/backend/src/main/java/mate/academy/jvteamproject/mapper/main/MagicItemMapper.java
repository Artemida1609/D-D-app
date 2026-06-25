package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.magic.MagicItemDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.MagicItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface MagicItemMapper extends GenericMapper<MagicItemDto, MagicItem> {
    MagicItem toEntity(MagicItemDto magicItemDto);

    MagicItemDto toDto(MagicItem magicItem);
}
