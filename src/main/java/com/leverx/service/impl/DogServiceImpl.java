package com.leverx.service.impl;

import static com.leverx.dto.converter.DogConverterDto.convertDogEntityToResponse;
import static com.leverx.dto.converter.DogConverterDto.convertDogRequestToEntity;
import static com.leverx.dto.converter.DogConverterDto.convertListOfEntityToListOfResponse;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.leverx.dto.request.DogRequestDto;
import com.leverx.dto.response.DogResponseDto;
import com.leverx.entity.Dog;
import com.leverx.repository.DogRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.DogService;

/** @author Andrei Yahorau */
@Service
public class DogServiceImpl implements DogService {

  private final DogRepository dogRepository;
  private final UserRepository userRepository;

  public DogServiceImpl(final DogRepository dogRepository, final UserRepository userRepository) {
    this.dogRepository = dogRepository;
    this.userRepository = userRepository;
  }

  @Override
  public DogResponseDto save(final DogRequestDto dogRequestDto) {

    Dog dog = convertDogRequestToEntity(dogRequestDto);
    dog.setOwner(
        userRepository
            .findById(dogRequestDto.getOwnerId())
            .orElseThrow(EntityNotFoundException::new));

    return convertDogEntityToResponse(dogRepository.save(dog));
  }

  @Override
  public DogResponseDto update(final long dogId, final DogRequestDto dogRequestDto) {

    if (!dogRepository.existsById(dogId)) {
      throw new EntityNotFoundException("No entity with such id");
    }

    Dog dog = convertDogRequestToEntity(dogRequestDto);
    dog.setOwner(
        userRepository
            .findById(dogRequestDto.getOwnerId())
            .orElseThrow(EntityNotFoundException::new));
    dog.setId(dogId);

    return convertDogEntityToResponse(dogRepository.save(dog));
  }

  @Override
  public DogResponseDto findById(final long id) {
    return convertDogEntityToResponse(
        dogRepository.findById(id).orElseThrow(EntityNotFoundException::new));
  }

  @Override
  public List<DogResponseDto> getAllByOwnerId(final long ownerId) {
    return convertListOfEntityToListOfResponse(dogRepository.findAllByOwnerId(ownerId));
  }

  @Override
  public List<DogResponseDto> getAll() {
    return convertListOfEntityToListOfResponse(dogRepository.findAll());
  }

  @Override
  public void delete(final long id) {
    dogRepository.deleteById(id);
  }
}
