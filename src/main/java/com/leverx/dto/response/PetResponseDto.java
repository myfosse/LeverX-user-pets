package com.leverx.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.leverx.dto.response.simple.SimpleUserResponseDto;
import com.leverx.entity.EPetType;

/** @author Andrei Yahorau */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponseDto {

  private long id;

  private EPetType petType;

  private String name;

  private LocalDate birthdate;

  private SimpleUserResponseDto owner;
}
