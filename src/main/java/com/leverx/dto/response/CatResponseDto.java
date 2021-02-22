package com.leverx.dto.response;

import java.time.LocalDate;

import com.leverx.dto.response.simple.SimpleUserResponseDto;
import com.leverx.entity.EPetType;

import lombok.Builder;
import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class CatResponseDto extends PetResponseDto {

  private boolean isBold;

  @Builder(builderMethodName = "catResponseBuilder")
  public CatResponseDto(
          final long id,
          final EPetType petType,
          final String name,
          final LocalDate birthdate,
          final SimpleUserResponseDto owner,
          final boolean isBold) {
    super(id, petType, name, birthdate, owner);
    this.isBold = isBold;
  }
}
