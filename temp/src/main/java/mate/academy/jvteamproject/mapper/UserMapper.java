package mate.academy.jvteamproject.mapper;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.user.UserDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationRequestDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationResponseDto;
import mate.academy.jvteamproject.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toEntity(UserRegistrationRequestDto requestDto);

    UserRegistrationResponseDto toRegistrationDto(User user);

    UserDto toDto(User user);
}
