package com.leverx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leverx.model.entity.EPetType;
import com.leverx.model.entity.Pet;

/** @author Andrei Yahorau */
public interface PetRepository extends JpaRepository<Pet, Long> {

  List<Pet> findAllByOwnerIdAndPetType(final long id, final EPetType petType);

  @Modifying
  @Query(value = "update pets set owner_id = null where owner_id = :ownerId", nativeQuery =
          true)
  void removeAllOwnerIdReference(@Param("ownerId") final long ownerId);
}
