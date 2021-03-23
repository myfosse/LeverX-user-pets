package com.leverx.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Entity
@Table(name = "dogs")
@PrimaryKeyJoinColumn(name = "dogId")
@Data
@NoArgsConstructor
public class Dog extends Pet {

  private boolean guideDog;

  @Builder(builderMethodName = "dogBuilder")
  public Dog(
      final long id,
      final EPetType petType,
      final String name,
      final Date birthdate,
      final User owner,
      final boolean guideDog) {
    super(id, petType, name, birthdate, owner);
    this.guideDog = guideDog;
  }
}
