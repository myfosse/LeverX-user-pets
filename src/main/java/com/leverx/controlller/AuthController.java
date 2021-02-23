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

/** @author Andrei Yahorau */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final UserService userService;

  @Autowired
  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<?> signUp(
      @Valid @RequestBody final UserRequestDto userRequestDto, final HttpServletRequest request) {

    UserResponseDto userResponseDto = userService.save(userRequestDto);

    return ResponseEntity.created(
            URI.create(
                request
                    .getRequestURL().toString()
                    .replace("auth/sign-up", "users/" + userResponseDto.getId())))
        .body(userResponseDto);
  }
}
