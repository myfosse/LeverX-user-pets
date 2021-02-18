package com.leverx.dto.converter;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.leverx.dto.request.UserRequestDto;
import com.leverx.dto.response.UserResponseDto;
import com.leverx.entity.User;

/** @author Andrei Yahorau */
public final class UserConverterDto {

  public static User convertUserRequestToEntity(final UserRequestDto userRequestDto) {
    return User.builder()
        .firstName(userRequestDto.getFirstName())
        .lastName(userRequestDto.getLastName())
        .email(userRequestDto.getEmail())
        .birthdate(userRequestDto.getBirthdate())
        .build();
  }

  public static UserResponseDto convertUserEntityToResponse(final User user) {
    return UserResponseDto.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .birthdate(user.getBirthdate())
        .build();
  }

  public static List<UserResponseDto> convertListOfEntityToListOfResponse(final List<User> userList) {
    return userList.stream()
            .map(UserConverterDto::convertUserEntityToResponse)
            .collect(toList());
  }
}
