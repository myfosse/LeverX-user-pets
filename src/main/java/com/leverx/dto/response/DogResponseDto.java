package com.leverx.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class DogResponseDto extends PetResponseDto {

  private boolean isGuideDog;

  @Builder(builderMethodName = "dogResponseBuilder")
  public DogResponseDto(
          final long id,
          final String name,
          final LocalDate birthdate,
          final UserResponseDto owner,
          final boolean isGuideDog) {
    super(id, name, birthdate, owner);
    this.isGuideDog = isGuideDog;
  }
}
