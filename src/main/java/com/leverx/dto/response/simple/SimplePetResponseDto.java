package com.leverx.dto.response.simple;

import java.time.LocalDate;

import com.leverx.entity.EPetType;

import lombok.Builder;
import lombok.Data;

/** @author Andrei Yahorau */
@Data
@Builder
public class SimplePetResponseDto {

  private long id;

  private EPetType petType;

  private String name;

  private LocalDate birthdate;
}
