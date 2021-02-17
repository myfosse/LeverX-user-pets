package com.leverx.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/** @author Andrei Yahorau */
public class UserRequestDto {

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @Email
  private String email;
}
