package com.leverx.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/** @author Andrei Yahorau */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponseDto {

  private long id;

  private String name;

  private LocalDate birthdate;

  private UserResponseDto owner;
}
