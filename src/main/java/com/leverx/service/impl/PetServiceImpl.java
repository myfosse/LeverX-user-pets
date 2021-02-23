package com.leverx.service.impl;

import static com.leverx.dto.converter.PetConverterDto.convertPetEntityToResponse;
import static com.leverx.dto.converter.PetConverterDto.convertListOfEntityToListOfResponse;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.dto.response.PetResponseDto;
import com.leverx.repository.PetRepository;
import com.leverx.service.PetService;

/** @author Andrei Yahorau */
@Service
public class PetServiceImpl implements PetService {

  private final PetRepository petRepository;

  @Autowired
  public PetServiceImpl(final PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @Override
  @Transactional
  public PetResponseDto findById(final long id) {
    return convertPetEntityToResponse(
        petRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such pet")));
  }

  @Override
  @Transactional
  public List<PetResponseDto> getAll() {
    return convertListOfEntityToListOfResponse(petRepository.findAll());
  }

  @Override
  @Transactional
  public void delete(final long id) {
    if (!petRepository.existsById(id)) {
      throw new EntityNotFoundException("There is no such pet");
    }
    petRepository.deleteById(id);
  }
}
