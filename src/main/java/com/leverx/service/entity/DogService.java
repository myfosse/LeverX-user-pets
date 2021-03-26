package com.leverx.service.entity;

import java.util.List;

import com.leverx.payload.dto.request.DogRequestDto;
import com.leverx.payload.dto.response.DogResponseDto;

/** @author Andrei Yahorau */
public interface DogService {

  DogResponseDto save(final DogRequestDto catRequestDto);

  DogResponseDto update(final long catId, final DogRequestDto catRequestDto);

  DogResponseDto findById(final long id);

  List<DogResponseDto> getAll();

  void delete(final long id);
}
