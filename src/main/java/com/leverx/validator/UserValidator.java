package com.leverx.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leverx.payload.dto.request.UserRequestDto;
import com.leverx.repository.UserRepository;

/** @author Andrei Yahorau */
@Component
public class UserValidator {

  private final UserRepository userRepository;

  @Autowired
  public UserValidator(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void validateUserRequest(final UserRequestDto userRequestDto) {

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
