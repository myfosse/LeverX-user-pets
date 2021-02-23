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
import org.springframework.web.bind.annotation.RestController;

import com.leverx.dto.request.DogRequestDto;
import com.leverx.dto.response.DogResponseDto;
import com.leverx.payload.response.MessageResponse;
import com.leverx.service.DogService;

/** @author Andrei Yahorau */
@RestController
@RequestMapping("/api/v1/dogs")
public class DogController {

  private final DogService dogService;

  @Autowired
  public DogController(final DogService dogService) {
    this.dogService = dogService;
  }

  @GetMapping
  public ResponseEntity<List<DogResponseDto>> getAll() {
    return ResponseEntity.ok(dogService.getAll());
  }

  @PostMapping
  public ResponseEntity<DogResponseDto> add(
      @Valid @RequestBody final DogRequestDto dogRequestDto, final HttpServletRequest request) {

    DogResponseDto dogResponseDto = dogService.save(dogRequestDto);
    String dogCreatedLink = request.getRequestURL() + "/" + dogResponseDto.getId();

    return ResponseEntity.created(URI.create(dogCreatedLink)).body(dogResponseDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DogResponseDto> getById(@PathVariable final long id) {
    return ResponseEntity.ok(dogService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<DogResponseDto> update(
      @PathVariable final long id, @Valid @RequestBody final DogRequestDto dogRequestDto) {
    return ResponseEntity.ok(dogService.update(id, dogRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<MessageResponse> delete(@PathVariable final long id) {
    dogService.delete(id);
    return ResponseEntity.ok(new MessageResponse("Dog deleted"));
  }
}
