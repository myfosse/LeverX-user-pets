package com.leverx.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class PetRequestDto {

  @NotNull
  private String name;

  @PastOrPresent
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthdate;

  @Email
  @NotNull
  private String ownerEmail;
}
