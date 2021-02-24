package com.leverx.dto.response.simple;

import java.time.LocalDate;

import com.leverx.model.entity.EPetType;

import lombok.Builder;
import lombok.Data;

/** @author Andrei Yahorau */
@Data
@Builder(builderMethodName = "simplePetResponseBuilder")
public class SimplePetResponseDto {

  private long id;

  private EPetType petType;

  private String name;

  private LocalDate birthdate;
}
