package com.leverx.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Entity
@Table(name = "cats")
@PrimaryKeyJoinColumn(name = "catId")
@Data
@NoArgsConstructor
public class Cat extends Pet {

  private boolean isBold;

  @Builder(builderMethodName = "catBuilder")
  public Cat(
      final long id,
      final EPetType petType,
      final String name,
      final LocalDate birthdate,
      final User owner,
      final boolean isBold) {
    super(id, petType, name, birthdate, owner);
    this.isBold = isBold;
  }
}
