package com.leverx.controlller;

import static com.leverx.constant.controller.ControllerConstant.ENDPOINT_PETS;

import java.util.List;

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

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@RestController
@RequestMapping(ENDPOINT_PETS)
@Slf4j
public class PetController {

  private final PetService petService;

  @Autowired
  public PetController(final PetService petService) {
    this.petService = petService;
  }

  @GetMapping
  public ResponseEntity<List<PetResponseDto>> getAll() {
    log.info("Get all pets request");

    return ResponseEntity.ok(petService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PetResponseDto> getById(@PathVariable final long id) {
    log.info("Get pet by id request: {}", id);

    return ResponseEntity.ok(petService.findById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<MessageResponse> deleteById(@PathVariable final long id) {
    log.info("Delete pet by id request: {}", id);

    petService.delete(id);
    return ResponseEntity.ok(new MessageResponse("Pet deleted"));
  }
}
