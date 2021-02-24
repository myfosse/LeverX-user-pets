package com.leverx.controlller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leverx.dto.request.UserRequestDto;
import com.leverx.dto.response.PetResponseDto;
import com.leverx.dto.response.UserResponseDto;
import com.leverx.payload.response.MessageResponse;
import com.leverx.service.UserService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDto>> getAll() {
    log.info("Get all users request");

    return ResponseEntity.ok(userService.getAll());
  }

  @PostMapping
  public ResponseEntity<UserResponseDto> add(
      @Valid @RequestBody final UserRequestDto userRequestDto, final HttpServletRequest request) {
    log.info("Create add request: {}", userRequestDto);

    UserResponseDto userResponseDto = userService.save(userRequestDto);
    String userCreatedLink = request.getRequestURL().toString() + "/" + userResponseDto.getId();

    return ResponseEntity.created(URI.create(userCreatedLink)).body(userResponseDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDto> getById(@PathVariable final long id) {
    log.info("Get user by id request: {}", id);

    return ResponseEntity.ok(userService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDto> update(
      @PathVariable final long id, @Valid @RequestBody final UserRequestDto userRequestDto) {
    log.info("Update user by id {} request: {}", id, userRequestDto);

    return ResponseEntity.ok(userService.update(id, userRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<MessageResponse> delete(@PathVariable final long id) {
    log.info("Delete user by id request: {}", id);

    userService.delete(id);
    return ResponseEntity.ok(new MessageResponse("User deleted"));
  }

  @GetMapping("/{id}/pets")
  public ResponseEntity<List<PetResponseDto>> getAllUserPets(
      @PathVariable final long id,
      @RequestParam(value = "type", required = false) final String type) {
    log.info("Get user pets by type request: {}", type);

    return ResponseEntity.ok(userService.getAllPets(id, type));
  }
}
