package com.leverx.service;

import java.util.List;

import com.leverx.dto.request.CatRequestDto;
import com.leverx.dto.response.CatResponseDto;

/** @author Andrei Yahorau */
public interface CatService {

  CatResponseDto save(final CatRequestDto catRequestDto);

  CatResponseDto update(final long catId, final CatRequestDto catRequestDto);

  CatResponseDto findById(final long id);

  List<CatResponseDto> getAll();

  void delete(final long id);
}
