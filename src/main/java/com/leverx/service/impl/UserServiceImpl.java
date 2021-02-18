package com.leverx.service.impl;

import static com.leverx.dto.converter.UserConverterDto.convertListOfEntityToListOfResponse;
import static com.leverx.dto.converter.UserConverterDto.convertUserEntityToResponse;
import static com.leverx.dto.converter.UserConverterDto.convertUserRequestToEntity;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.leverx.dto.request.UserRequestDto;
import com.leverx.dto.response.UserResponseDto;
import com.leverx.entity.User;
import com.leverx.repository.UserRepository;
import com.leverx.service.UserService;

/** @author Andrei Yahorau */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserResponseDto save(final UserRequestDto userRequestDto) {
    return convertUserEntityToResponse(
            userRepository.save(convertUserRequestToEntity(userRequestDto)));
  }

  @Override
  public UserResponseDto update(final long userId, final UserRequestDto userRequestDto) {

    if (!userRepository.existsById(userId)) {
      throw new EntityNotFoundException("No entity with such id");
    }

    User user = convertUserRequestToEntity(userRequestDto);
    user.setId(userId);

    return convertUserEntityToResponse(userRepository.save(user));
  }

  @Override
  public UserResponseDto findById(final long id) {
    return convertUserEntityToResponse(
        userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
  }

  @Override
  public List<UserResponseDto> getAll() {
    return convertListOfEntityToListOfResponse(userRepository.findAll());
  }

  @Override
  public void delete(final long id) {
    userRepository.deleteById(id);
  }
}
