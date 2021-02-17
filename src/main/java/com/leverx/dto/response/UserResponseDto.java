package com.leverx.dto.response;

import java.util.List;

/** @author Andrei Yahorau */
public class UserResponseDto {

  private long id;

  private String firstName;

  private String lastName;

  private String email;

  private List<PetResponseDto> pets;
}
