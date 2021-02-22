package com.leverx.service.impl;

import static com.leverx.dto.converter.UserConverterDto.convertListOfEntityToListOfResponse;
import static com.leverx.dto.converter.UserConverterDto.convertUserEntityToResponse;
import static com.leverx.dto.converter.UserConverterDto.convertUserRequestToEntity;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.dto.request.UserRequestDto;
import com.leverx.dto.response.UserResponseDto;
import com.leverx.entity.User;
import com.leverx.exception.UserNotFoundException;
import com.leverx.repository.PetRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.UserService;

/** @author Andrei Yahorau */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PetRepository petRepository;

  @Autowired
  public UserServiceImpl(final UserRepository userRepository, final PetRepository petRepository) {
    this.userRepository = userRepository;
    this.petRepository = petRepository;
  }

  @Override
  @Transactional
  public UserResponseDto save(final UserRequestDto userRequestDto) {
    return convertUserEntityToResponse(
            userRepository.save(convertUserRequestToEntity(userRequestDto)));
  }

  @Override
  @Transactional
  public UserResponseDto update(final long userId, final UserRequestDto userRequestDto) {

    if (!userRepository.existsById(userId)) {
      throw new UserNotFoundException("No entity with such id");
    }

    User user = convertUserRequestToEntity(userRequestDto);
    user.setId(userId);

    return convertUserEntityToResponse(userRepository.save(user));
  }

  @Override
  @Transactional
  public Optional<UserResponseDto> findById(final long id) {
    return Optional.of(convertUserEntityToResponse(
        userRepository.findById(id).orElseThrow(UserNotFoundException::new)));
  }

  @Override
  @Transactional
  public List<UserResponseDto> getAll() {
    return convertListOfEntityToListOfResponse(userRepository.findAll());
  }

  @Override
  @Transactional
  public void delete(final long id) {
    petRepository.removeAllOwnerIdReference(id);
    userRepository.deleteById(id);
  }
}
