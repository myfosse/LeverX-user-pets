package com.leverx.converter.dto;

import static java.util.stream.Collectors.toList;

import static com.leverx.converter.dto.UserConverterDto.convertUserEntityToSimpleResponse;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.leverx.payload.dto.request.CatRequestDto;
import com.leverx.payload.dto.response.CatResponseDto;
import com.leverx.model.entity.Cat;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class CatConverterDto {

  public static Cat convertCatRequestToEntity(final CatRequestDto catRequestDto) {
    return Cat.catBuilder()
        .name(catRequestDto.getName())
        .birthdate(catRequestDto.getBirthdate())
        .bold(catRequestDto.isBold())
        .build();
  }

  public static CatResponseDto convertCatEntityToResponse(final Cat cat) {
    return CatResponseDto.catResponseBuilder()
        .id(cat.getId())
        .petType(cat.getPetType())
        .name(cat.getName())
        .birthdate(cat.getBirthdate())
        .bold(cat.isBold())
        .owner(convertUserEntityToSimpleResponse(cat.getOwner()))
        .build();
  }

  public static List<CatResponseDto> convertCatListOfEntityToListOfResponse(final List<Cat> catList) {
    return catList.stream()
        .map(CatConverterDto::convertCatEntityToResponse)
        .collect(toList());
  }
}
