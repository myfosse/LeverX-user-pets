package com.leverx.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

/** @author Andrei Yahorau */
public class DogRequestDto {

  @NotNull
  private String name;

  @PastOrPresent
  private LocalDate birthdate;

  @Positive
  private long ownerId;

  @NotNull
  private boolean isGuideDog;
}
