package com.leverx.service.impl;

import static com.leverx.dto.converter.CatConverterDto.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.leverx.dto.request.CatRequestDto;
import com.leverx.dto.response.CatResponseDto;
import com.leverx.entity.Cat;
import com.leverx.repository.CatRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.CatService;
import org.springframework.stereotype.Service;

/** @author Andrei Yahorau */
@Service
public class CatServiceImpl implements CatService {

  private final CatRepository catRepository;
  private final UserRepository userRepository;

  public CatServiceImpl(final CatRepository catRepository, final UserRepository userRepository) {
    this.catRepository = catRepository;
    this.userRepository = userRepository;
  }

  @Override
  public CatResponseDto save(final CatRequestDto catRequestDto) {

    Cat cat = convertCatRequestToEntity(catRequestDto);
    cat.setOwner(
        userRepository
            .findById(catRequestDto.getOwnerId())
            .orElseThrow(EntityNotFoundException::new));

    return convertCatEntityToResponse(catRepository.save(cat));
  }

  @Override
  public CatResponseDto update(final long catId, final CatRequestDto catRequestDto) {

    if (!catRepository.existsById(catId)) {
      throw new EntityNotFoundException("No entity with such id");
    }
    Cat cat = convertCatRequestToEntity(catRequestDto);
    cat.setOwner(
            userRepository
                    .findById(catRequestDto.getOwnerId())
                    .orElseThrow(EntityNotFoundException::new));
    cat.setId(catId);

    return convertCatEntityToResponse(catRepository.save(cat));
  }

  @Override
  public CatResponseDto findById(final long id) {
    return convertCatEntityToResponse(
        catRepository.findById(id).orElseThrow(EntityNotFoundException::new));
  }

  @Override
  public List<CatResponseDto> getAllByOwnerId(final long ownerId) {
    return convertListOfEntityToListOfResponse(catRepository.findAllByOwnerId(ownerId));
  }

  @Override
  public List<CatResponseDto> getAll() {
    return convertListOfEntityToListOfResponse(catRepository.findAll());
  }

  @Override
  public void delete(final long id) {
    catRepository.deleteById(id);
  }
}
