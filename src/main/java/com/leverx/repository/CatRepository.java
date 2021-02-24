package com.leverx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leverx.model.entity.Cat;

/** @author Andrei Yahorau */
public interface CatRepository extends JpaRepository<Cat, Long> {}
