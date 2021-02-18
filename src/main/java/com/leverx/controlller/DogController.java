package com.leverx.controlller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leverx.dto.request.DogRequestDto;
import com.leverx.payload.response.MessageResponse;
import com.leverx.service.DogService;

/** @author Andrei Yahorau */
@RestController
@RequestMapping("/api/v1/dogs")
public class DogController {

  private final DogService dogService;

  @Autowired
  public DogController(DogService dogService) {
    this.dogService = dogService;
  }

  @GetMapping
  public @ResponseBody ResponseEntity<?> getAllDogs(
      @RequestParam(value = "ownerId", required = false) final Optional<Long> ownerId) {
    return new ResponseEntity<>(
        ownerId.isPresent() ? dogService.getAllByOwnerId(ownerId.get()) : dogService.getAll(), OK);
  }

  @PostMapping
  public @ResponseBody ResponseEntity<?> addDog(
      @Valid @RequestBody final DogRequestDto dogRequestDto) {
    return new ResponseEntity<>(dogService.save(dogRequestDto), CREATED);
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<?> getDogById(@PathVariable final long id) {
    return new ResponseEntity<>(dogService.findById(id), OK);
  }

  @PutMapping("/{id}")
  public @ResponseBody ResponseEntity<?> updateDog(
      @PathVariable final long id, @Valid @RequestBody final DogRequestDto dogRequestDto) {
    return new ResponseEntity<>(dogService.update(id, dogRequestDto), ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public @ResponseBody ResponseEntity<?> deleteDog(@PathVariable final long id) {
    dogService.delete(id);
    return new ResponseEntity<>(new MessageResponse("Dog deleted"), ACCEPTED);
  }
}
