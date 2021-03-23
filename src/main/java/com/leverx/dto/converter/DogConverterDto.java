package com.leverx.dto.converter;

import static java.util.stream.Collectors.toList;

import static com.leverx.dto.converter.UserConverterDto.convertUserEntityToSimpleResponse;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.leverx.dto.request.DogRequestDto;
import com.leverx.dto.response.DogResponseDto;
import com.leverx.model.entity.Dog;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class DogConverterDto {

  public static Dog convertDogRequestToEntity(final DogRequestDto dogRequestDto) {
    return Dog.dogBuilder()
        .name(dogRequestDto.getName())
        .birthdate(dogRequestDto.getBirthdate())
        .guideDog(dogRequestDto.isGuideDog())
        .build();
  }

  public static DogResponseDto convertDogEntityToResponse(final Dog dog) {
    return DogResponseDto.dogResponseBuilder()
        .id(dog.getId())
        .petType(dog.getPetType())
        .name(dog.getName())
        .birthdate(dog.getBirthdate())
        .guideDog(dog.isGuideDog())
        .owner(convertUserEntityToSimpleResponse(dog.getOwner()))
        .build();
  }

  public static List<DogResponseDto> convertDogListOfEntityToListOfResponse(final List<Dog> dogList) {
    return dogList.stream()
        .map(DogConverterDto::convertDogEntityToResponse)
        .collect(toList());
  }
}
