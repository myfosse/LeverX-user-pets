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
  @Pattern(
      regexp =
          "(?=.*[0-9])(?=.*[\\.!?@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z\\.!@#$%^&*]{8," + "256}",
      message =
          "Password must contain lowercase and uppercase latin letters, numbers, special "
              + "symbols and be at least 8 characters")
  private String password;

  @NotNull
  @Pattern(
      regexp =
          "(?=.*[0-9])(?=.*[\\.!?@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z\\.!@#$%^&*]{8," + "256}",
      message =
          "Password must contain lowercase and uppercase latin letters, numbers, special "
              + "symbols and be at least 8 characters")
  private String passwordConfirmation;

  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthdate;
}
