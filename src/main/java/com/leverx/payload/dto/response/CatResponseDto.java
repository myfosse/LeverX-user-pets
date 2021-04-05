package com.leverx.payload.dto.response;

import java.time.LocalDate;
import java.util.Date;

import com.leverx.payload.dto.response.simple.SimpleUserResponseDto;
import com.leverx.model.entity.EPetType;

import lombok.Builder;
import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class CatResponseDto extends PetResponseDto {

  private boolean bold;

  @Builder(builderMethodName = "catResponseBuilder")
  public CatResponseDto(
          final long id,
          final EPetType petType,
          final String name,
          final LocalDate birthdate,
          final SimpleUserResponseDto owner,
          final boolean bold) {
    super(id, petType, name, birthdate, owner);
    this.bold = bold;
  }
}
