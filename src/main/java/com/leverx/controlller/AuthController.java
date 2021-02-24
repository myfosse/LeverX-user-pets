package com.leverx.controlller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leverx.dto.request.UserRequestDto;
import com.leverx.dto.response.UserResponseDto;
import com.leverx.service.UserService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

  private final UserService userService;

  @Autowired
  public AuthController(final UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<?> signUp(
      @Valid @RequestBody final UserRequestDto userRequestDto, final HttpServletRequest request) {
    log.info("Sign up request: {}", userRequestDto);

    UserResponseDto userResponseDto = userService.save(userRequestDto);
    String createdUserLink =
        request
            .getRequestURL()
            .toString()
            .replace("auth/sign-up", "users/" + userResponseDto.getId());

    return ResponseEntity.created(URI.create(createdUserLink)).body(userResponseDto);
  }
}
