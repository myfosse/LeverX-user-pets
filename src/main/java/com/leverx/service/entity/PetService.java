package com.leverx.service.entity;

import java.util.List;

import com.leverx.payload.dto.response.PetResponseDto;

/** @author Andrei Yahorau */
public interface PetService {

  PetResponseDto findById(final long id);

  List<PetResponseDto> getAll();

  void delete(final long id);
}
