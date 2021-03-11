package com.leverx.dto.response;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.leverx.dto.response.simple.SimpleUserResponseDto;
import com.leverx.model.entity.EPetType;

/** @author Andrei Yahorau */
@Data
@Builder(builderMethodName = "petResponseBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class PetResponseDto {

  private long id;

  private EPetType petType;

  private String name;

  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate birthdate;

  private SimpleUserResponseDto owner;
}
