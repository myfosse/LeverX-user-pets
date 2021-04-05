package com.leverx.converter.odata;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import static com.leverx.converter.date.DateConverter.toDate;
import static com.leverx.converter.odata.UserConverterOData.convertUserEntityToSimpleOData;

import static lombok.AccessLevel.PRIVATE;

import java.util.Collections;
import java.util.List;

import com.leverx.model.entity.Dog;
import com.leverx.model.entity.EPetType;
import com.leverx.model.entity.Pet;
import com.leverx.model.odata.PetOData;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class PetConverterOData {

  public static PetOData convertPetEntityToOData(final Pet pet) {
    return PetOData.builder()
        .id(pet.getId())
        .userId(pet.getOwner().getId())
        .petType(pet.getPetType().name())
        .name(pet.getName())
        .birthdate(toDate(pet.getBirthdate()))
        .owner(convertUserEntityToSimpleOData(pet.getOwner()))
        .build();
  }

  public static Pet convertPetODataToEntity(final PetOData pet) {
    return Dog.dogBuilder()
        .name(pet.getName())
        .petType(EPetType.valueOf(pet.getPetType().toUpperCase()))
        .build();
  }

  public static List<PetOData> convertPetListOfEntityToListOfOData(final List<Pet> petList) {
    return isNull(petList)
        ? Collections.emptyList()
        : petList.stream().map(PetConverterOData::convertPetEntityToOData).collect(toList());
  }
}
