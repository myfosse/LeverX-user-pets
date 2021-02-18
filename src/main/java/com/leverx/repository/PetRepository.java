package com.leverx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leverx.entity.Pet;

/** @author Andrei Yahorau */
public interface PetRepository extends JpaRepository<Pet, Long> {

  List<Pet> findAllByOwnerId(final long ownerId);
}
