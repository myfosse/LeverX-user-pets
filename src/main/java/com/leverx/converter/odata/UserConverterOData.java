package com.leverx.converter.odata;

import static java.util.stream.Collectors.toList;

import static com.leverx.converter.date.DateConverter.toDate;
import static com.leverx.converter.odata.PetConverterOData.convertPetListOfEntityToListOfOData;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.leverx.model.entity.User;
import com.leverx.model.odata.UserOData;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class UserConverterOData {

  public static User convertUserODataToEntity(final UserOData user) {
    return User.userBuilder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .password(user.getPassword())
        .build();
  }

  public static UserOData convertUserEntityToOData(final User user) {
    return UserOData.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .birthdate(toDate(user.getBirthdate()))
        .role(user.getRole().name())
        .pets(convertPetListOfEntityToListOfOData(user.getPets()))
        .build();
  }

  public static UserOData convertUserEntityToSimpleOData(final User user) {
    return UserOData.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .birthdate(toDate(user.getBirthdate()))
        .role(user.getRole().name())
        .build();
  }

  public static List<UserOData> convertUserListOfEntityToListOfOData(final List<User> userList) {
    return userList.stream()
        .map(UserConverterOData::convertUserEntityToOData)
        .collect(toList());
  }
}
