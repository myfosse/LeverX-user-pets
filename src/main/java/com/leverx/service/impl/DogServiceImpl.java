package com.leverx.service.impl;

import static com.leverx.dto.converter.DogConverterDto.convertDogEntityToResponse;
import static com.leverx.dto.converter.DogConverterDto.convertDogRequestToEntity;
import static com.leverx.dto.converter.DogConverterDto.convertDogListOfEntityToListOfResponse;
import static com.leverx.model.entity.EPetType.DOG;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.dto.request.DogRequestDto;
import com.leverx.dto.response.DogResponseDto;
import com.leverx.model.entity.Dog;
import com.leverx.repository.DogRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.DogService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class DogServiceImpl implements DogService {

  private final DogRepository dogRepository;
  private final UserRepository userRepository;

  @Autowired
  public DogServiceImpl(final DogRepository dogRepository, final UserRepository userRepository) {
    this.dogRepository = dogRepository;
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public DogResponseDto save(final DogRequestDto dogRequestDto) {
    log.info("Save dog to database: {}", dogRequestDto);

    Dog dog = convertDogRequestToEntity(dogRequestDto);
    dog.setOwner(
        userRepository
            .findById(dogRequestDto.getOwnerId())
            .orElseThrow(() -> new EntityNotFoundException("There is no such user")));
    dog.setPetType(DOG);

    return convertDogEntityToResponse(dogRepository.save(dog));
  }

  @Override
  @Transactional
  public DogResponseDto update(final long dogId, final DogRequestDto dogRequestDto) {
    log.info("Update dog {} in database by id: {}", dogRequestDto, dogId);

    Dog dog = convertDogRequestToEntity(dogRequestDto);
    dog.setOwner(
        userRepository
            .findById(dogRequestDto.getOwnerId())
            .orElseThrow(() -> new EntityNotFoundException("There is no such user")));
    dog.setId(
        dogRepository
            .findById(dogId)
            .orElseThrow(() -> new EntityNotFoundException("There is no such dog"))
            .getId());
    dog.setPetType(DOG);

    return convertDogEntityToResponse(dogRepository.save(dog));
  }

  @Override
  @Transactional
  public DogResponseDto findById(final long id) {
    log.info("Find dog in database by id: {}", id);

    return convertDogEntityToResponse(
        dogRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such dog")));
  }

  @Override
  @Transactional
  public List<DogResponseDto> getAll() {
    log.info("Get all dogs from database");

    return convertDogListOfEntityToListOfResponse(dogRepository.findAll());
  }

  @Override
  @Transactional
  public void delete(final long id) {
    log.info("Delete dog from database by id: {}", id);

    if (!dogRepository.existsById(id)) {
      log.error("No dog in database with id: {}", id);

      throw new EntityNotFoundException("There is no such dog");
    }
    dogRepository.deleteById(id);
  }
}
