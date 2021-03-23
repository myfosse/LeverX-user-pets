package com.leverx.dto.request;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import lombok.Data;

/** @author Andrei Yahorau */
@Data
public class PetRequestDto {

  @NotNull
  private String name;

  @PastOrPresent
  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
  @JsonSerialize(using = DateSerializer.class)
  private Date birthdate;

  @Positive
  private long ownerId;
}
