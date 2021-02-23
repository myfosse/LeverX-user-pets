package com.leverx.service;

import java.util.List;
import java.util.Optional;

import com.leverx.dto.response.PetResponseDto;

/** @author Andrei Yahorau */
public interface PetService {

  Optional<PetResponseDto> findById(final long id);

  List<PetResponseDto> getAll();

  List<PetResponseDto> getAllByOwnerId(final long ownerId);

  List<PetResponseDto> findAllByOwnerIdAndPetType(final long id, final String type);

  void delete(final long id);
}
