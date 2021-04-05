package com.leverx.converter.odata;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import static com.leverx.converter.date.DateConverter.toDate;
import static com.leverx.converter.odata.UserConverterOData.convertUserEntityToSimpleOData;

import static lombok.AccessLevel.PRIVATE;

import java.util.Collections;
import java.util.List;

import com.leverx.model.entity.Cat;
import com.leverx.model.odata.CatOData;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class CatConverterOData {

  public static Cat convertCatODataToEntity(final CatOData cat) {
    return Cat.catBuilder()
        .name(cat.getName())
        .bold(cat.getBold())
        .build();
  }

  public static CatOData convertCatEntityToOData(final Cat cat) {
    return CatOData.catBuilder()
        .id(cat.getId())
        .userId(cat.getOwner().getId())
        .owner(convertUserEntityToSimpleOData(cat.getOwner()))
        .name(cat.getName())
        .petType(cat.getPetType().name())
        .birthdate(toDate(cat.getBirthdate()))
        .bold(cat.isBold())
        .build();
  }

  public static List<CatOData> convertCatListOfEntityToListOfOData(final List<Cat> catList) {
    return  isNull(catList)
        ? Collections.emptyList()
        : catList.stream()
        .map(CatConverterOData::convertCatEntityToOData)
        .collect(toList());
  }
}
