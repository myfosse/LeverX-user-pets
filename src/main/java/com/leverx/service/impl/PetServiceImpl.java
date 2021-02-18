package com.leverx.service.impl;

import static com.leverx.dto.converter.PetConverterDto.convertPetEntityToResponse;
import static com.leverx.dto.converter.PetConverterDto.convertPetRequestToEntity;
import static com.leverx.dto.converter.PetConverterDto.convertListOfEntityToListOfResponse;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.leverx.dto.request.PetRequestDto;
import com.leverx.dto.response.PetResponseDto;
import com.leverx.entity.Pet;
import com.leverx.repository.PetRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.PetService;

/** @author Andrei Yahorau */
@Service
public class PetServiceImpl implements PetService {

  private final PetRepository petRepository;
  private final UserRepository userRepository;

  public PetServiceImpl(final PetRepository petRepository, final UserRepository userRepository) {
    this.petRepository = petRepository;
    this.userRepository = userRepository;
  }

  @Override
  public PetResponseDto save(final PetRequestDto petRequestDto) {

    Pet pet = convertPetRequestToEntity(petRequestDto);
    pet.setOwner(
        userRepository
            .findById(petRequestDto.getOwnerId())
            .orElseThrow(EntityNotFoundException::new));

    return convertPetEntityToResponse(petRepository.save(pet));
  }

  @Override
  public PetResponseDto update(final long petId, final PetRequestDto petRequestDto) {

    if (!petRepository.existsById(petId)) {
      throw new EntityNotFoundException("No entity with such id");
    }
    Pet pet = convertPetRequestToEntity(petRequestDto);
    pet.setOwner(
            userRepository
                    .findById(petRequestDto.getOwnerId())
                    .orElseThrow(EntityNotFoundException::new));
    pet.setId(petId);

    return convertPetEntityToResponse(petRepository.save(pet));
  }

  @Override
  public PetResponseDto findById(final long id) {
    return convertPetEntityToResponse(
        petRepository.findById(id).orElseThrow(EntityNotFoundException::new));
  }

  @Override
  public List<PetResponseDto> getAllByOwnerId(final long ownerId) {
    return convertListOfEntityToListOfResponse(petRepository.findAllByOwnerId(ownerId));
  }

  @Override
  public List<PetResponseDto> getAll() {
    return convertListOfEntityToListOfResponse(petRepository.findAll());
  }

  @Override
  public void delete(final long id) {
    petRepository.deleteById(id);
  }
}
