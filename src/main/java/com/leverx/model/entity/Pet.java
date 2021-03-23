package com.leverx.model.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

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

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
  @JsonSerialize(using = DateSerializer.class)
  private Date birthdate;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "owner_id")
  private User owner;
}
