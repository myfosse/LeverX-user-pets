package com.leverx.dto.converter;

import static java.util.stream.Collectors.toList;

import static com.leverx.dto.converter.UserConverterDto.convertUserEntityToSimpleResponse;

import java.util.List;

import com.leverx.dto.request.DogRequestDto;
import com.leverx.dto.response.DogResponseDto;
import com.leverx.entity.Dog;

/** @author Andrei Yahorau */
public final class DogConverterDto {

  public static Dog convertDogRequestToEntity(final DogRequestDto dogRequestDto) {
    return Dog.dogBuilder()
        .name(dogRequestDto.getName())
        .birthdate(dogRequestDto.getBirthdate())
        .isGuideDog(dogRequestDto.isGuideDog())
        .build();
  }

  public static DogResponseDto convertDogEntityToResponse(final Dog dog) {
    return DogResponseDto.dogResponseBuilder()
        .id(dog.getId())
        .petType(dog.getPetType())
        .name(dog.getName())
        .birthdate(dog.getBirthdate())
        .isGuideDog(dog.isGuideDog())
        .owner(convertUserEntityToSimpleResponse(dog.getOwner()))
        .build();
  }

  public static List<DogResponseDto> convertListOfEntityToListOfResponse(final List<Dog> dogList) {
    return dogList.stream()
            .map(DogConverterDto::convertDogEntityToResponse)
            .collect(toList());
  }
}
