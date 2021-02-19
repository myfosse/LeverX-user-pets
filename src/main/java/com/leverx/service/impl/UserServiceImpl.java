package com.leverx.service.impl;

import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;

import static com.leverx.dto.converter.UserConverterDto.convertListOfEntityToListOfResponse;
import static com.leverx.dto.converter.UserConverterDto.convertUserEntityToResponse;
import static com.leverx.dto.converter.UserConverterDto.convertUserRequestToEntity;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.dto.converter.PetConverterDto;
import com.leverx.dto.request.UserRequestDto;
import com.leverx.dto.response.PetResponseDto;
import com.leverx.dto.response.UserResponseDto;
import com.leverx.entity.EPetType;
import com.leverx.entity.User;
import com.leverx.repository.PetRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.UserService;

/** @author Andrei Yahorau */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PetRepository petRepository;

  @Autowired
  public UserServiceImpl(
      final UserRepository userRepository,
      final PetRepository petRepository) {
    this.userRepository = userRepository;
    this.petRepository = petRepository;
  }

  @Override
  @Transactional
  public UserResponseDto save(final UserRequestDto userRequestDto) {

    User user = convertUserRequestToEntity(userRequestDto);

    return convertUserEntityToResponse(userRepository.save(user));
  }

  @Override
  @Transactional
  public UserResponseDto update(final long userId, final UserRequestDto userRequestDto) {

    if (!userRepository.existsById(userId)) {
      throw new EntityNotFoundException("No entity with such id");
    }

    User user = convertUserRequestToEntity(userRequestDto);
    user.setId(userId);

    return convertUserEntityToResponse(userRepository.save(user));
  }

  @Override
  @Transactional
  public UserResponseDto findById(final long id) {
    return convertUserEntityToResponse(
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such user")));
  }

  @Override
  @Transactional
  public List<UserResponseDto> getAll() {
    return convertListOfEntityToListOfResponse(userRepository.findAll());
  }

  @Override
  @Transactional
  public List<PetResponseDto> getAllPets(final long id, final String petType) {

    if (nonNull(petType)
        && stream(EPetType.values())
            .anyMatch(type -> type.toString().equals(petType.toUpperCase()))) {
      return PetConverterDto.convertListOfEntityToListOfResponse(
          petRepository.findAllByOwnerIdAndPetType(id, EPetType.valueOf(petType.toUpperCase())));
    }
    return PetConverterDto.convertListOfEntityToListOfResponse(
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such user"))
            .getPets());
  }

  @Override
  @Transactional
  public void delete(final long id) {
    if (!userRepository.existsById(id)) {
      throw new EntityNotFoundException("There is no such user");
    }
    petRepository.removeAllOwnerIdReference(id);
    userRepository.deleteById(id);
  }
}
