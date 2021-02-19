package com.leverx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leverx.entity.Dog;

/** @author Andrei Yahorau */
public interface DogRepository extends JpaRepository<Dog, Long> {}
