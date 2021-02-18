package com.leverx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leverx.entity.Dog;

/** @author Andrei Yahorau */
public interface DogRepository extends JpaRepository<Dog, Long> {

  List<Dog> findAllByOwnerId(final long ownerId);
}
