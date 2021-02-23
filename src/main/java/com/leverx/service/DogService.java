package com.leverx.service;

import java.util.List;
import java.util.Optional;

import com.leverx.dto.request.DogRequestDto;
import com.leverx.dto.response.DogResponseDto;

/** @author Andrei Yahorau */
public interface DogService {

  DogResponseDto save(final DogRequestDto catRequestDto);

  DogResponseDto update(final long catId, final DogRequestDto catRequestDto);

  Optional<DogResponseDto> findById(final long id);

  List<DogResponseDto> getAll();

  void delete(final long id);
}
