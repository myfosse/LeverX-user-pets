package com.leverx.dto.response.simple;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

/** @author Andrei Yahorau */
@Data
@Builder
public class SimpleUserResponseDto {

  private long id;

  private String firstName;

  private String lastName;

  private String email;

  private LocalDate birthdate;
}
