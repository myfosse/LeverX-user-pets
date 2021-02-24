package com.leverx.validator;

import static lombok.AccessLevel.PRIVATE;

import com.leverx.dto.request.UserRequestDto;
import com.leverx.repository.UserRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
@Slf4j
public final class UserValidator {

  public static void validateUserRequest(
      final UserRequestDto userRequestDto, final UserRepository userRepository) {

    if (userRepository.existsByEmail(userRequestDto.getEmail())) {
      throw new IllegalArgumentException("Current email already taken");
    }

    if (!userRequestDto.getPassword().equals(userRequestDto.getPasswordConfirmation())) {
      throw new IllegalArgumentException("Passwords doesn't match");
    }

    if (userRequestDto.getPassword().equals(userRequestDto.getEmail())) {
      throw new IllegalArgumentException("Password should not be same with email");
    }
  }
}
