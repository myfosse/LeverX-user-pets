package com.leverx.dto.converter;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.leverx.dto.request.PetRequestDto;
import com.leverx.dto.response.PetResponseDto;
import com.leverx.entity.Pet;

/** @author Andrei Yahorau */
public final class PetConverterDto {

  public static Pet convertPetRequestToEntity(final PetRequestDto dogRequestDto) {
    return Pet.builder()
            .name(dogRequestDto.getName())
            .birthdate(dogRequestDto.getBirthdate())
            .build();
  }
  
  public static PetResponseDto convertPetEntityToResponse(final Pet pet) {
    return PetResponseDto.builder()
            .id(pet.getId())
            .name(pet.getName())
            .birthdate(pet.getBirthdate())
            .owner(UserConverterDto.convertUserEntityToResponse(pet.getOwner()))
            .build();
  }

  public static List<PetResponseDto> convertListOfEntityToListOfResponse(final List<Pet> petList) {
    return petList.stream()
            .map(PetConverterDto::convertPetEntityToResponse)
            .collect(toList());
  }
}
