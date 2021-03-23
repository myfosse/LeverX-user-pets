package com.leverx.dto.response.simple;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.leverx.model.entity.ERole;

import lombok.Builder;
import lombok.Data;

/** @author Andrei Yahorau */
@Data
@Builder(builderMethodName = "simpleUserResponseBuilder")
public class SimpleUserResponseDto {

  private long id;

  private String firstName;

  private String lastName;

  private String email;

  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
  @JsonSerialize(using = DateSerializer.class)
  private Date birthdate;

  private ERole role;
}
