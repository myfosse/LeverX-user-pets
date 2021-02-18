package com.leverx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leverx.entity.Cat;

/** @author Andrei Yahorau */
@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

  List<Cat> findAllByOwnerId(final long ownerId);
}
