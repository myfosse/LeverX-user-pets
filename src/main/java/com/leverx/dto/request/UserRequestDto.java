package com.leverx.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class UserRequestDto {

  @NotNull private String firstName;

  @NotNull private String lastName;

  @Email private String email;

  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthdate;
}
