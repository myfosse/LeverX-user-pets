package com.leverx.validator;

import static lombok.AccessLevel.PRIVATE;

import org.springframework.security.core.context.SecurityContextHolder;

import com.leverx.dto.request.UserRequestDto;
import com.leverx.model.UserDetailsImpl;
import com.leverx.repository.UserRepository;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
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

  public Long getAuthenticationUserId() {
    return SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
        ? ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            .getId()
        : 0;
  }
}