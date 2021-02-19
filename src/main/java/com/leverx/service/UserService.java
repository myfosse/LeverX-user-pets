package com.leverx.service;

import java.util.List;

import com.leverx.dto.request.UserRequestDto;
import com.leverx.dto.response.PetResponseDto;
import com.leverx.dto.response.UserResponseDto;

/** @author Andrei Yahorau */
public interface UserService {

  UserResponseDto save(final UserRequestDto petRequestDto);

  UserResponseDto update(final long catId, final UserRequestDto petRequestDto);

  UserResponseDto findById(final long id);

  List<UserResponseDto> getAll();

  List<PetResponseDto> getAllPets(final long id, final String petType);

  void delete(final long id);
}
