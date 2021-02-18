package com.leverx.service;

import java.util.List;

import com.leverx.dto.response.PetResponseDto;

/** @author Andrei Yahorau */
public interface PetService {

  PetResponseDto findById(final long id);

  List<PetResponseDto> getAllByOwnerId(final long ownerId);

  List<PetResponseDto> getAll();
}
