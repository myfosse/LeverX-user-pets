package com.leverx.service.impl;

import static com.leverx.dto.converter.PetConverterDto.convertPetEntityToResponse;
import static com.leverx.dto.converter.PetConverterDto.convertListOfEntityToListOfResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.dto.response.PetResponseDto;
import com.leverx.entity.EPetType;
import com.leverx.exception.NoSuchPetTypeException;
import com.leverx.exception.PetNotFoundException;
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
  public Optional<PetResponseDto> findById(final long id) {
    return Optional.of(
        convertPetEntityToResponse(
            petRepository.findById(id).orElseThrow(PetNotFoundException::new)));
  }

  @Override
  @Transactional
  public List<PetResponseDto> getAll() {
    return convertListOfEntityToListOfResponse(petRepository.findAll());
  }

  @Override
  @Transactional
  public List<PetResponseDto> getAllByOwnerId(final long ownerId) {
    return convertListOfEntityToListOfResponse(petRepository.findAllByOwnerId(ownerId));
  }

  @Override
  @Transactional
  public List<PetResponseDto> findAllByOwnerIdAndPetType(final long id, final String type) {
    if (Arrays.stream(EPetType.values()).anyMatch(t -> t.toString().equals(type.toUpperCase()))) {
      return convertListOfEntityToListOfResponse(
              petRepository.findAllByOwnerIdAndPetType(id, EPetType.valueOf(type.toUpperCase())));
    }
    throw new NoSuchPetTypeException();
  }

  @Override
  @Transactional
  public void delete(long id) {
    petRepository.deleteById(id);
  }
}
