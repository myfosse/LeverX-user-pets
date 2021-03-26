package com.leverx.service.entity.impl;

import static com.leverx.converter.dto.PetConverterDto.convertPetEntityToResponse;
import static com.leverx.converter.dto.PetConverterDto.convertPetListOfEntityToListOfResponse;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.payload.dto.response.PetResponseDto;
import com.leverx.repository.PetRepository;
import com.leverx.service.entity.PetService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class PetServiceImpl implements PetService {

  private final PetRepository petRepository;

  @Autowired
  public PetServiceImpl(final PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @Override
  @Transactional
  public PetResponseDto findById(final long id) {
    log.info("PetService. Find pet in database by id: {}", id);

    return convertPetEntityToResponse(
        petRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such pet")));
  }

  @Override
  @Transactional
  public List<PetResponseDto> getAll() {
    log.info("PetService.Get all pets from database");

    return convertPetListOfEntityToListOfResponse(petRepository.findAll());
  }

  @Override
  @Transactional
  public void delete(final long id) {
    log.info("PetService.Delete pet from database by id: {}", id);

    if (!petRepository.existsById(id)) {
      log.error("No pet in database with id: {}", id);

      throw new EntityNotFoundException("There is no such pet");
    }
    petRepository.deleteById(id);
  }
}
