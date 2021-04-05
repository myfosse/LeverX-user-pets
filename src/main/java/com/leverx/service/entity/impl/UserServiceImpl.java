package com.leverx.service.entity.impl;

import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;

import static com.leverx.converter.dto.UserConverterDto.convertUserListOfEntityToListOfResponse;
import static com.leverx.converter.dto.UserConverterDto.convertUserEntityToResponse;
import static com.leverx.converter.dto.UserConverterDto.convertUserRequestToEntity;
import static com.leverx.model.entity.ERole.ROLE_USER;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.converter.dto.PetConverterDto;
import com.leverx.payload.dto.request.UserRequestDto;
import com.leverx.payload.dto.response.PetResponseDto;
import com.leverx.payload.dto.response.UserResponseDto;
import com.leverx.model.entity.EPetType;
import com.leverx.model.entity.User;
import com.leverx.repository.PetRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.entity.UserService;
import com.leverx.validator.UserValidator;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PetRepository petRepository;
  private final UserValidator userValidator;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(
      final UserRepository userRepository,
      final PetRepository petRepository,
      final UserValidator userValidator,
      final PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.petRepository = petRepository;
    this.passwordEncoder = passwordEncoder;
    this.userValidator = userValidator;
  }

  @Override
  @Transactional
  public UserResponseDto save(final UserRequestDto userRequestDto) {
    log.info("Save user to database: {}", userRequestDto);

    userValidator.validateUserRequest(userRequestDto);

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

    userValidator.validateUserRequest(userRequestDto);

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

    return convertUserListOfEntityToListOfResponse(userRepository.findAll());
  }

  @Override
  @Transactional
  public List<PetResponseDto> getAllPets(final long id, final String petType) {
    log.info("Get all user pets from database by pet type: {}", petType);

    if (nonNull(petType)
        && stream(EPetType.values())
            .anyMatch(type -> type.toString().equals(petType.toUpperCase()))) {
      return PetConverterDto.convertPetListOfEntityToListOfResponse(
          petRepository.findAllByOwnerIdAndPetType(id, EPetType.valueOf(petType.toUpperCase())));
    }
    return PetConverterDto.convertPetListOfEntityToListOfResponse(
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
