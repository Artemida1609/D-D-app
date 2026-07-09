package mate.academy.jvteamproject.mapper.impl;

import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.user.UserDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationRequestDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationResponseDto;
import mate.academy.jvteamproject.mapper.UserMapper;
import mate.academy.jvteamproject.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRegistrationRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        User user = new User();

        if ( requestDto.getEmail() != null ) {
            user.setEmail( requestDto.getEmail() );
        }
        if ( requestDto.getPassword() != null ) {
            user.setPassword( requestDto.getPassword() );
        }
        if ( requestDto.getFirstName() != null ) {
            user.setFirstName( requestDto.getFirstName() );
        }
        if ( requestDto.getLastName() != null ) {
            user.setLastName( requestDto.getLastName() );
        }

        return user;
    }

    @Override
    public UserRegistrationResponseDto toRegistrationDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserRegistrationResponseDto userRegistrationResponseDto = new UserRegistrationResponseDto();

        if ( user.getId() != null ) {
            userRegistrationResponseDto.setId( user.getId() );
        }
        if ( user.getEmail() != null ) {
            userRegistrationResponseDto.setEmail( user.getEmail() );
        }
        if ( user.getFirstName() != null ) {
            userRegistrationResponseDto.setFirstName( user.getFirstName() );
        }
        if ( user.getLastName() != null ) {
            userRegistrationResponseDto.setLastName( user.getLastName() );
        }

        return userRegistrationResponseDto;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        if ( user.getEmail() != null ) {
            userDto.setEmail( user.getEmail() );
        }
        if ( user.getFirstName() != null ) {
            userDto.setFirstName( user.getFirstName() );
        }
        if ( user.getLastName() != null ) {
            userDto.setLastName( user.getLastName() );
        }

        return userDto;
    }
}
