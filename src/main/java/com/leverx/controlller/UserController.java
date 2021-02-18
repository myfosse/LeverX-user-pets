package com.leverx.controlller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.ACCEPTED;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leverx.dto.request.UserRequestDto;
import com.leverx.payload.response.MessageResponse;
import com.leverx.service.PetService;
import com.leverx.service.UserService;

/** @author Andrei Yahorau */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;
  private final PetService petService;

  @Autowired
  public UserController(UserService userService, PetService petService) {
    this.userService = userService;
    this.petService = petService;
  }

  @GetMapping
  public @ResponseBody ResponseEntity<?> getAllUsers() {
    return new ResponseEntity<>(userService.getAll(), OK);
  }

  @PostMapping
  public @ResponseBody ResponseEntity<?> addUser(
      @Valid @RequestBody final UserRequestDto userRequestDto) {
    return new ResponseEntity<>(userService.save(userRequestDto), CREATED);
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<?> getUserById(@PathVariable final long id) {
    return new ResponseEntity<>(userService.findById(id), OK);
  }

  @PutMapping("/{id}")
  public @ResponseBody ResponseEntity<?> updateUser(
      @PathVariable final long id, @Valid @RequestBody final UserRequestDto userRequestDto) {
    return new ResponseEntity<>(userService.update(id, userRequestDto), ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public @ResponseBody ResponseEntity<?> deleteUser(@PathVariable final long id) {
    userService.delete(id);
    return new ResponseEntity<>(new MessageResponse("User deleted"), ACCEPTED);
  }

  @GetMapping("/{id}/pets")
  public @ResponseBody ResponseEntity<?> getAllUserPets(@PathVariable final long id) {
    return new ResponseEntity<>(petService.getAllByOwnerId(id), OK);
  }
}
