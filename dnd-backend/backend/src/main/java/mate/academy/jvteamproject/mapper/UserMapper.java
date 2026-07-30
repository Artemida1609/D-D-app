package mate.academy.jvteamproject.mapper;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.user.UpdateProfileRequestDto;
import mate.academy.jvteamproject.dto.user.UserDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationRequestDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationResponseDto;
import mate.academy.jvteamproject.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toEntity(UserRegistrationRequestDto requestDto);

    UserRegistrationResponseDto toRegistrationDto(User user);

    void updateUserFromDto(UpdateProfileRequestDto requestDto, @MappingTarget User user);

    UserDto toDto(User user);
}
