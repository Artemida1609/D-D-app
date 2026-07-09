package mate.academy.jvteamproject.mapper;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.saved.SavedElementRequestDto;
import mate.academy.jvteamproject.dto.saved.SavedElementResponseDto;
import mate.academy.jvteamproject.model.SavedElement;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SavedElementMapper {
    SavedElement toEntity(SavedElementRequestDto requestDto);

    SavedElementResponseDto toResponseDto(SavedElement entity);
}
