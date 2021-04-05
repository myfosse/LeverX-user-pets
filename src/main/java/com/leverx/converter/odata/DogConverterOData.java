package com.leverx.converter.odata;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import static com.leverx.converter.date.DateConverter.toDate;
import static com.leverx.converter.odata.UserConverterOData.convertUserEntityToSimpleOData;

import static lombok.AccessLevel.PRIVATE;

import java.util.Collections;
import java.util.List;

import com.leverx.model.entity.Dog;
import com.leverx.model.odata.DogOData;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class DogConverterOData {

  public static Dog convertDogODataToEntity(final DogOData dog) {
    return Dog.dogBuilder()
        .name(dog.getName())
        .guideDog(dog.getGuideDog())
        .build();
  }

  public static DogOData convertDogEntityToOData(final Dog dog) {
    return DogOData.dogBuilder()
        .id(dog.getId())
        .userId(dog.getOwner().getId())
        .owner(convertUserEntityToSimpleOData(dog.getOwner()))
        .name(dog.getName())
        .petType(dog.getPetType().name())
        .birthdate(toDate(dog.getBirthdate()))
        .guideDog(dog.isGuideDog())
        .build();
  }

  public static List<DogOData> convertDogListOfEntityToListOfOData(final List<Dog> dogList) {
    return isNull(dogList)
        ? Collections.emptyList()
        : dogList.stream()
        .map(DogConverterOData::convertDogEntityToOData)
        .collect(toList());
  }
}
