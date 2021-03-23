package com.leverx.dto.response;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.leverx.dto.response.simple.SimplePetResponseDto;
import com.leverx.model.entity.ERole;

import lombok.Builder;
import lombok.Data;

/** @author Andrei Yahorau */
@Data
@Builder(builderMethodName = "userResponseBuilder")
public class UserResponseDto {

  private long id;

  private String firstName;

  private String lastName;

  private String email;

  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
  @JsonSerialize(using = DateSerializer.class)
  private Date birthdate;

  private List<SimplePetResponseDto> pets;

  private ERole role;
}
