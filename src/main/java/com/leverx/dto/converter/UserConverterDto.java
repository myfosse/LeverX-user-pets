package com.leverx.dto.converter;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import static com.leverx.dto.converter.PetConverterDto.convertPetListOfEntityToListOfSimpleResponse;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.leverx.dto.request.UserRequestDto;
import com.leverx.dto.response.simple.SimpleUserResponseDto;
import com.leverx.dto.response.UserResponseDto;
import com.leverx.model.entity.User;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class UserConverterDto {

  public static User convertUserRequestToEntity(final UserRequestDto userRequestDto) {
    return User.userBuilder()
        .firstName(userRequestDto.getFirstName())
        .lastName(userRequestDto.getLastName())
        .email(userRequestDto.getEmail())
        .birthdate(userRequestDto.getBirthdate())
        .build();
  }

  public static UserResponseDto convertUserEntityToResponse(final User user) {
    return UserResponseDto.userResponseBuilder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .birthdate(user.getBirthdate())
        .role(user.getRole())
        .pets(convertPetListOfEntityToListOfSimpleResponse(user.getPets()))
        .build();
  }

  public static SimpleUserResponseDto convertUserEntityToSimpleResponse(final User user) {
    return isNull(user)
        ? null
        : SimpleUserResponseDto.simpleUserResponseBuilder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole())
            .birthdate(user.getBirthdate())
            .build();
  }

  public static List<UserResponseDto> convertUserListOfEntityToListOfResponse(
      final List<User> userList) {
    return userList.stream()
        .map(UserConverterDto::convertUserEntityToResponse)
        .collect(toList());
  }
}
