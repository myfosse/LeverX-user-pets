package com.leverx.controlller;

import static com.leverx.constant.controller.ControllerConstant.ENDPOINT_CATS;

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

import com.leverx.dto.request.CatRequestDto;
import com.leverx.dto.response.CatResponseDto;
import com.leverx.payload.response.MessageResponse;
import com.leverx.service.CatService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@RestController
@RequestMapping(ENDPOINT_CATS)
@Slf4j
public class CatController {

  private final CatService catService;

  @Autowired
  public CatController(final CatService catService) {
    this.catService = catService;
  }

  @GetMapping
  public ResponseEntity<List<CatResponseDto>> getAll() {
    log.info("Get all cats request");

    return ResponseEntity.ok(catService.getAll());
  }

  @PostMapping
  public ResponseEntity<CatResponseDto> add(
      @Valid @RequestBody final CatRequestDto catRequestDto, final HttpServletRequest request) {
    log.info("Create cat request: {}", catRequestDto);

    CatResponseDto catResponseDto = catService.save(catRequestDto);
    String createdCatLink = request.getRequestURL().toString() + "/" + catResponseDto.getId();

    return ResponseEntity.created(URI.create(createdCatLink)).body(catResponseDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CatResponseDto> getById(@PathVariable final long id) {
    log.info("Get cat by id request: {}", id);

    return ResponseEntity.ok().body(catService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CatResponseDto> update(
      @PathVariable final long id, @Valid @RequestBody final CatRequestDto catRequestDto) {
    log.info("Update cat by id {} request: {}", id, catRequestDto);

    return ResponseEntity.ok().body(catService.update(id, catRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<MessageResponse> delete(@PathVariable final long id) {
    log.info("Delete cat by id request: {}", id);

    catService.delete(id);
    return ResponseEntity.ok().body(new MessageResponse("Cat deleted"));
  }
}
