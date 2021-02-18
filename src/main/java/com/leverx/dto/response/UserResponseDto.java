package com.leverx.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

/** @author Andrei Yahorau */
@Data
@Builder
public class UserResponseDto {

  private long id;

  private String firstName;

  private String lastName;

  private String email;

  private LocalDate birthdate;
}
