package com.leverx.service.impl;

import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;

import static com.leverx.dto.converter.UserConverterDto.convertListOfEntityToListOfResponse;
import static com.leverx.dto.converter.UserConverterDto.convertUserEntityToResponse;
import static com.leverx.dto.converter.UserConverterDto.convertUserRequestToEntity;
import static com.leverx.entity.ERole.ROLE_USER;
import static com.leverx.validator.UserValidator.validateUserRequest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PetRepository petRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(
      final UserRepository userRepository,
      final PetRepository petRepository,
      final PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.petRepository = petRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public UserResponseDto save(final UserRequestDto userRequestDto) {
    log.info("Save user to database: {}", userRequestDto);

    validateUserRequest(userRequestDto, userRepository);

    User user = convertUserRequestToEntity(userRequestDto);
    String password = passwordEncoder.encode(userRequestDto.getPassword());
    user.setPassword(password);
    user.setRole(ROLE_USER);

    return convertUserEntityToResponse(userRepository.save(user));
  }

  @Override
  @Transactional
  public UserResponseDto update(final long userId, final UserRequestDto userRequestDto) {
    log.info("Update user {} in database by id: {}", userRequestDto, userId);

    if (!userRepository.existsById(userId)) {
      throw new EntityNotFoundException("No entity with such id");
    }

    validateUserRequest(userRequestDto, userRepository);

    User user = convertUserRequestToEntity(userRequestDto);
    String password = passwordEncoder.encode(userRequestDto.getPassword());
    user.setId(userId);
    user.setPassword(password);
    user.setRole(ROLE_USER);

    return convertUserEntityToResponse(userRepository.save(user));
  }

  @Override
  @Transactional
  public UserResponseDto findById(final long id) {
    log.info("Find user in database by id: {}", id);

    return convertUserEntityToResponse(
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such user")));
  }

  @Override
  @Transactional
  public List<UserResponseDto> getAll() {
    log.info("Get all users from database");

    return convertListOfEntityToListOfResponse(userRepository.findAll());
  }

  @Override
  @Transactional
  public List<PetResponseDto> getAllPets(final long id, final String petType) {
    log.info("Get all user pets from database by pet type: {}", petType);

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
    log.info("Delete user from database by id: {}", id);

    if (!userRepository.existsById(id)) {
      log.error("No user in database with id: {}", id);

      throw new EntityNotFoundException("There is no such user");
    }
    petRepository.removeAllOwnerIdReference(id);
    userRepository.deleteById(id);
  }
}
