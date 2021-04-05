package com.leverx.controlller;

import static com.leverx.constant.controller.ControllerConstants.ENDPOINT_AUTH;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leverx.payload.dto.request.UserRequestDto;
import com.leverx.payload.dto.response.UserResponseDto;
import com.leverx.service.entity.UserService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@RestController
@RequestMapping(ENDPOINT_AUTH)
@Slf4j
public class AuthController {

  private final UserService userService;

  @Autowired
  public AuthController(final UserService userService) {
    this.userService = userService;
  }

  @PostMapping("sign-up")
  public ResponseEntity<UserResponseDto> signUp(
      @Valid @RequestBody final UserRequestDto userRequestDto, final HttpServletRequest request) {
    log.info("AuthController. Sign up request: {}", userRequestDto);

    UserResponseDto userResponseDto = userService.save(userRequestDto);
    String createdUserLink =
        request
            .getRequestURL()
            .toString()
            .replace("auth/sign-up", "users/" + userResponseDto.getId());

    return ResponseEntity.created(URI.create(createdUserLink)).body(userResponseDto);
  }
}
