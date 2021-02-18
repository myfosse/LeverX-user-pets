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

import com.leverx.dto.request.CatRequestDto;
import com.leverx.payload.response.MessageResponse;
import com.leverx.service.CatService;

/** @author Andrei Yahorau */
@RestController
@RequestMapping("/api/v1/cats")
public class CatController {

  private final CatService catService;

  @Autowired
  public CatController(CatService catService) {
    this.catService = catService;
  }

  @GetMapping
  public @ResponseBody ResponseEntity<?> getAllCats(
      @RequestParam(value = "ownerId", required = false) final Optional<Long> ownerId) {
    return new ResponseEntity<>(
        ownerId.isPresent() ? catService.getAllByOwnerId(ownerId.get()) : catService.getAll(), OK);
  }

  @PostMapping
  public @ResponseBody ResponseEntity<?> addCat(
      @Valid @RequestBody final CatRequestDto catRequestDto) {
    return new ResponseEntity<>(catService.save(catRequestDto), CREATED);
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<?> getCatById(@PathVariable final long id) {
    return new ResponseEntity<>(catService.findById(id), OK);
  }

  @PutMapping("/{id}")
  public @ResponseBody ResponseEntity<?> updateCat(
      @PathVariable final long id, @Valid @RequestBody final CatRequestDto catRequestDto) {
    return new ResponseEntity<>(catService.update(id, catRequestDto), ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public @ResponseBody ResponseEntity<?> deleteCat(@PathVariable final long id) {
    catService.delete(id);
    return new ResponseEntity<>(new MessageResponse("Cat deleted"), ACCEPTED);
  }
}
