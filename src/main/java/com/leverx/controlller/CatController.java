package com.leverx.controlller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.leverx.dto.request.CatRequestDto;
import com.leverx.dto.response.CatResponseDto;
import com.leverx.payload.response.MessageResponse;
import com.leverx.service.CatService;

/** @author Andrei Yahorau */
@RestController
@RequestMapping("/api/v1/cats")
public class CatController {

  private final CatService catService;

  @Autowired
  public CatController(final CatService catService) {
    this.catService = catService;
  }

  @GetMapping
  public ResponseEntity<List<CatResponseDto>> getAllCats() {
    return ResponseEntity.ok(catService.getAll());
  }

  @PostMapping
  public ResponseEntity<CatResponseDto> addCat(
          @Valid @RequestBody final CatRequestDto catRequestDto, final HttpServletRequest request) {

    CatResponseDto catResponseDto = catService.save(catRequestDto);

    return ResponseEntity.created(
            URI.create(request.getRequestURL().toString() + "/" + catResponseDto.getId()))
            .body(catResponseDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<CatResponseDto>> getCatById(@PathVariable final long id) {
    return ResponseEntity.ok().body(catService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CatResponseDto> updateCat(
      @PathVariable final long id, @Valid @RequestBody final CatRequestDto catRequestDto) {
    return ResponseEntity.ok().body(catService.update(id, catRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<MessageResponse> deleteCat(@PathVariable final long id) {
    catService.delete(id);
    return ResponseEntity.ok().body(new MessageResponse("Cat deleted"));
  }
}
