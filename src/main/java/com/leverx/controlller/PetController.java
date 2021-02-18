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

import com.leverx.dto.request.PetRequestDto;
import com.leverx.payload.response.MessageResponse;
import com.leverx.service.PetService;

/** @author Andrei Yahorau */
@RestController
@RequestMapping("/api/v1/pets")
public class PetController {

  private final PetService petService;

  @Autowired
  public PetController(PetService petService) {
    this.petService = petService;
  }

  @GetMapping
  public @ResponseBody ResponseEntity<?> getAllPets(
      @RequestParam(value = "ownerId", required = false) final Optional<Long> ownerId) {
    return new ResponseEntity<>(
        ownerId.isPresent() ? petService.getAllByOwnerId(ownerId.get()) : petService.getAll(), OK);
  }

  @PostMapping
  public @ResponseBody ResponseEntity<?> addPet(
      @Valid @RequestBody final PetRequestDto petRequestDto) {
    return new ResponseEntity<>(petService.save(petRequestDto), CREATED);
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<?> getPetById(@PathVariable final long id) {
    return new ResponseEntity<>(petService.findById(id), OK);
  }

  @PutMapping("/{id}")
  public @ResponseBody ResponseEntity<?> updatePet(
      @PathVariable final long id, @Valid @RequestBody final PetRequestDto petRequestDto) {
    return new ResponseEntity<>(petService.update(id, petRequestDto), ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public @ResponseBody ResponseEntity<?> deletePet(@PathVariable final long id) {
    petService.delete(id);
    return new ResponseEntity<>(new MessageResponse("Pet deleted"), ACCEPTED);
  }
}
