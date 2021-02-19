package com.leverx.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Entity
@Table(name = "pets")
@Inheritance(strategy = JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pet {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Enumerated(STRING)
  private EPetType petType;

  private String name;

  private LocalDate birthdate;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "owner_id")
  private User owner;
}
