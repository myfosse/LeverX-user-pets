package com.leverx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leverx.entity.Pet;

/** @author Andrei Yahorau */
public interface PetRepository extends JpaRepository<Pet, Long> {}
