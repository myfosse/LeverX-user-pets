package com.leverx.service;

import java.util.List;

import com.leverx.dto.request.PetRequestDto;
import com.leverx.dto.response.PetResponseDto;

/** @author Andrei Yahorau */
public interface PetService {

  PetResponseDto save(final PetRequestDto petRequestDto);

  PetResponseDto update(final long catId, final PetRequestDto petRequestDto);

  PetResponseDto findById(final long id);

  List<PetResponseDto> getAllByOwnerId(final long ownerId);

  List<PetResponseDto> getAll();

  void delete(final long id);
}
