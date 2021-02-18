package com.leverx.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Entity
@PrimaryKeyJoinColumn(name = "dogId")
@Data
@NoArgsConstructor
public class Dog extends Pet {

  private boolean isGuideDog;

  @Builder(builderMethodName = "dogBuilder")
  public Dog(
      final long id,
      final String name,
      final LocalDate birthdate,
      final User owner,
      final boolean isGuideDog) {
    super(id, name, birthdate, owner);
    this.isGuideDog = isGuideDog;
  }
}
