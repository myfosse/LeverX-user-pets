package com.leverx.payload.dto.request;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class UserRequestDto {

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @Email
  private String email;

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
  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate birthdate;
}
