package com.leverx.controlller;

import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<?> getPetById(@PathVariable final long id) {
    return new ResponseEntity<>(petService.findById(id), OK);
  }
}
