package com.leverx.service.odata.impl;

import static java.time.LocalDate.now;

import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

import static com.leverx.constant.odata.ODataModelConstants.ENTITY_SET_NAME_USERS;
import static com.leverx.converter.odata.PetConverterOData.convertPetEntityToOData;
import static com.leverx.converter.odata.PetConverterOData.convertPetListOfEntityToListOfOData;
import static com.leverx.converter.odata.PetConverterOData.convertPetODataToEntity;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leverx.model.entity.Pet;
import com.leverx.model.entity.User;
import com.leverx.model.odata.PetOData;
import com.leverx.repository.PetRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.odata.PetODataService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class PetODataServiceImpl implements PetODataService {

  private final PetRepository petRepository;
  private final UserRepository userRepository;

  @Autowired
  public PetODataServiceImpl(final PetRepository petRepository,
                             final UserRepository userRepository) {
    this.petRepository = petRepository;
    this.userRepository = userRepository;

  }

  @Override
  public Object getRelatedData(final Object sourceData, final String targetEntityName) throws ODataNotFoundException {
    log.info("Get related data");

    PetOData pet = (PetOData) sourceData;
    if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
      return pet.getOwner();
    }
    throw new ODataNotFoundException(ENTITY);
  }

  @Override
  public PetOData findById(final long id) {
    log.info("Find pet in database by id: {}", id);

    return convertPetEntityToOData(
        petRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such pet")));
  }

  @Override
  public List<PetOData> findAll() {
    log.info("Get all pets from database");

    return convertPetListOfEntityToListOfOData(petRepository.findAll());
  }

  @Override
  public void deleteById(final long id) {
    log.info("Delete pet by id");

    petRepository.deleteById(id);
  }

  @Override
  public void save(final PetOData odataEntity) {
    log.info("Save new pet {}", odataEntity);

    Pet pet = convertPetODataToEntity(odataEntity);
    User user = userRepository
        .findById(odataEntity.getUserId())
        .orElseThrow(() -> new EntityNotFoundException("No user with such id"));
    pet.setOwner(user);
    pet.setBirthdate(now());

    petRepository.save(pet);
  }

  @Override
  public PetOData getODataObject() {
    log.info("Get ODataObject");

    return new PetOData();
  }
}
