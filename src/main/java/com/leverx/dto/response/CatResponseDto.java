package com.leverx.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class CatResponseDto extends PetResponseDto {

  private boolean isBold;

  @Builder(builderMethodName = "catResponseBuilder")
  public CatResponseDto(
          final long id,
          final String name,
          final LocalDate birthdate,
          final UserResponseDto owner,
          final boolean isBold) {
    super(id, name, birthdate, owner);
    this.isBold = isBold;
  }
}
