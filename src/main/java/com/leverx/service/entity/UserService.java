package com.leverx.service.entity;

import java.util.List;

import com.leverx.payload.dto.request.UserRequestDto;
import com.leverx.payload.dto.response.PetResponseDto;
import com.leverx.payload.dto.response.UserResponseDto;

/** @author Andrei Yahorau */
public interface UserService {

  UserResponseDto save(final UserRequestDto petRequestDto);

  UserResponseDto update(final long catId, final UserRequestDto petRequestDto);

  UserResponseDto findById(final long id);

  List<UserResponseDto> getAll();

  List<PetResponseDto> getAllPets(final long id, final String petType);

  void delete(final long id);
}
