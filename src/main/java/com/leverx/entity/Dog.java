package com.leverx.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Entity
@PrimaryKeyJoinColumn(name = "dogId")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Dog extends Pet {

  private boolean isGuideDog;
}
