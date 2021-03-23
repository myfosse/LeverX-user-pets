package com.leverx.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class CatRequestDto extends PetRequestDto {

  @NotNull
  private boolean bold;
}
