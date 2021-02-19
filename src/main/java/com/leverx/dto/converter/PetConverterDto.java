package com.leverx.dto.converter;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

import com.leverx.dto.response.PetResponseDto;
import com.leverx.dto.response.simple.SimplePetResponseDto;
import com.leverx.entity.Pet;

/** @author Andrei Yahorau */
public final class PetConverterDto {

  public static PetResponseDto convertPetEntityToResponse(final Pet pet) {
    return PetResponseDto.builder()
        .id(pet.getId())
        .petType(pet.getPetType())
        .name(pet.getName())
        .birthdate(pet.getBirthdate())
        .owner(UserConverterDto.convertUserEntityToSimpleResponse(pet.getOwner()))
        .build();
  }

  public static SimplePetResponseDto convertPetEntityToSimpleResponse(final Pet pet) {
    return SimplePetResponseDto.builder()
            .id(pet.getId())
            .petType(pet.getPetType())
            .name(pet.getName())
            .birthdate(pet.getBirthdate())
            .build();
  }

  public static List<PetResponseDto> convertListOfEntityToListOfResponse(final List<Pet> petList) {
    return petList == null
        ? Collections.emptyList()
        : petList.stream()
            .map(PetConverterDto::convertPetEntityToResponse)
            .collect(toList());
  }

  public static List<SimplePetResponseDto> convertListOfEntityToListOfSimpleResponse(final List<Pet> petList) {
    return petList == null
            ? Collections.emptyList()
            : petList.stream()
            .map(PetConverterDto::convertPetEntityToSimpleResponse)
            .collect(toList());
  }
}
