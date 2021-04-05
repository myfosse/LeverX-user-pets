package com.leverx.converter.dto;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import static com.leverx.converter.dto.UserConverterDto.convertUserEntityToSimpleResponse;

import static lombok.AccessLevel.PRIVATE;

import java.util.Collections;
import java.util.List;

import com.leverx.payload.dto.response.PetResponseDto;
import com.leverx.payload.dto.response.simple.SimplePetResponseDto;
import com.leverx.model.entity.Pet;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class PetConverterDto {

  public static PetResponseDto convertPetEntityToResponse(final Pet pet) {
    return PetResponseDto.petResponseBuilder()
        .id(pet.getId())
        .petType(pet.getPetType())
        .name(pet.getName())
        .birthdate(pet.getBirthdate())
        .owner(convertUserEntityToSimpleResponse(pet.getOwner()))
        .build();
  }

  public static SimplePetResponseDto convertPetEntityToSimpleResponse(final Pet pet) {
    return SimplePetResponseDto.simplePetResponseBuilder()
        .id(pet.getId())
        .petType(pet.getPetType())
        .name(pet.getName())
        .birthdate(pet.getBirthdate())
        .build();
  }

  public static List<PetResponseDto> convertPetListOfEntityToListOfResponse(final List<Pet> petList) {
    return isNull(petList)
        ? Collections.emptyList()
        : petList.stream().map(PetConverterDto::convertPetEntityToResponse).collect(toList());
  }

  public static List<SimplePetResponseDto> convertPetListOfEntityToListOfSimpleResponse(
      final List<Pet> petList) {
    return isNull(petList)
        ? Collections.emptyList()
        : petList.stream().map(PetConverterDto::convertPetEntityToSimpleResponse).collect(toList());
  }
}
