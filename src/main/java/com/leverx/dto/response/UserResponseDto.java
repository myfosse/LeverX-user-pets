package com.leverx.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.leverx.dto.response.simple.SimplePetResponseDto;
import com.leverx.entity.ERole;

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

  private List<SimplePetResponseDto> pets;

  private ERole role;
}
