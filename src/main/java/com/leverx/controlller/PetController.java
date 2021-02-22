package com.leverx.controlller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leverx.dto.response.PetResponseDto;
import com.leverx.payload.response.MessageResponse;
import com.leverx.service.PetService;

/** @author Andrei Yahorau */
@RestController
@RequestMapping("/api/v1/pets")
public class PetController {

  private final PetService petService;

  @Autowired
  public PetController(final PetService petService) {
    this.petService = petService;
  }

  @GetMapping
  public ResponseEntity<List<PetResponseDto>> getAllPets() {
    return ResponseEntity.ok(petService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<PetResponseDto>> getPetById(@PathVariable final long id) {
    return ResponseEntity.ok(petService.findById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<MessageResponse> deletePetById(@PathVariable final long id) {
    petService.delete(id);
    return ResponseEntity.ok(new MessageResponse("Pet deleted"));
  }
}
