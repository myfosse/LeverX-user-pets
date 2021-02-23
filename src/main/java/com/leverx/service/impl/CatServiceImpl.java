package com.leverx.service.impl;

import static com.leverx.dto.converter.CatConverterDto.*;
import static com.leverx.entity.EPetType.CAT;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.leverx.dto.request.CatRequestDto;
import com.leverx.dto.response.CatResponseDto;
import com.leverx.entity.Cat;
import com.leverx.repository.CatRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.CatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Andrei Yahorau */
@Service
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
    return convertCatEntityToResponse(
        catRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such cat")));
  }

  @Override
  @Transactional
  public List<CatResponseDto> getAll() {
    return convertListOfEntityToListOfResponse(catRepository.findAll());
  }

  @Override
  @Transactional
  public void delete(final long id) {
    if (!catRepository.existsById(id)) {
      throw new EntityNotFoundException("There is no such dog");
    }
    catRepository.deleteById(id);
  }
}
