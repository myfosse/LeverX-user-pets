package com.leverx.payload.dto.response.simple;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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

  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate birthdate;
}
