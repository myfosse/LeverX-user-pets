package com.leverx.service.entity.impl;

import static com.leverx.converter.dto.CatConverterDto.convertCatEntityToResponse;
import static com.leverx.converter.dto.CatConverterDto.convertCatListOfEntityToListOfResponse;
import static com.leverx.converter.dto.CatConverterDto.convertCatRequestToEntity;
import static com.leverx.model.entity.EPetType.CAT;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.model.entity.Cat;
import com.leverx.payload.dto.request.CatRequestDto;
import com.leverx.payload.dto.response.CatResponseDto;
import com.leverx.repository.CatRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.entity.CatService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class CatServiceImpl implements CatService {

  private final CatRepository catRepository;
  private final UserRepository userRepository;

  @Autowired
  public CatServiceImpl(final CatRepository catRepository, final UserRepository userRepository) {
    this.catRepository = catRepository;
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public CatResponseDto save(final CatRequestDto catRequestDto) {
    log.info("CatService. Save cat to database: {}", catRequestDto);

    Cat cat = convertCatRequestToEntity(catRequestDto);
    cat.setOwner(
        userRepository
            .findById(catRequestDto.getOwnerId())
            .orElseThrow(() -> new EntityNotFoundException("There is no such user")));
    cat.setPetType(CAT);

    return convertCatEntityToResponse(catRepository.save(cat));
  }

  @Override
  @Transactional
  public CatResponseDto update(final long catId, final CatRequestDto catRequestDto) {
    log.info("CatService. Update cat {} in database by id: {}", catRequestDto, catId);

    Cat cat = convertCatRequestToEntity(catRequestDto);
    cat.setOwner(
        userRepository
            .findById(catRequestDto.getOwnerId())
            .orElseThrow(() -> new EntityNotFoundException("There is no such user")));
    cat.setId(
        catRepository
            .findById(catId)
            .orElseThrow(() -> new EntityNotFoundException("There is no such cat"))
            .getId());
    cat.setPetType(CAT);

    return convertCatEntityToResponse(catRepository.save(cat));
  }

  @Override
  @Transactional
  public CatResponseDto findById(final long id) {
    log.info("CatService. Find cat in database by id: {}", id);

    return convertCatEntityToResponse(
        catRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such cat")));
  }

  @Override
  @Transactional
  public List<CatResponseDto> getAll() {
    log.info("CatService. Get all cats from database");

    return convertCatListOfEntityToListOfResponse(catRepository.findAll());
  }

  @Override
  @Transactional
  public void delete(final long id) {
    log.info("CatService. Delete cat from database by id: {}", id);

    if (!catRepository.existsById(id)) {
      log.error("No cat in database with id: {}", id);

      throw new EntityNotFoundException("There is no such dog");
    }
    catRepository.deleteById(id);
  }
}
