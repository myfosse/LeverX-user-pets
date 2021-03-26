package com.leverx.service.odata.impl;

import static java.time.LocalDate.now;

import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

import static com.leverx.converter.odata.DogConverterOData.convertDogEntityToOData;
import static com.leverx.converter.odata.DogConverterOData.convertDogListOfEntityToListOfOData;
import static com.leverx.converter.odata.DogConverterOData.convertDogODataToEntity;
import static com.leverx.model.entity.EPetType.DOG;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.model.entity.Dog;
import com.leverx.model.entity.User;
import com.leverx.model.odata.DogOData;
import com.leverx.model.odata.UserOData;
import com.leverx.repository.DogRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.odata.DogODataService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class DogODataServiceImpl implements DogODataService {

  private final DogRepository dogRepository;
  private final UserRepository userRepository;

  @Autowired
  public DogODataServiceImpl(final DogRepository dogRepository,
                             final UserRepository userRepository) {
    this.dogRepository = dogRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Object getRelatedData(final Object sourceData, final String targetEntityName)
      throws ODataNotFoundException {
    log.info("DogODataService. Get related data");

    throw new ODataNotFoundException(ENTITY);
  }

  @Override
  public DogOData findById(final long id) {
    log.info("DogODataService. Find dog in database by id: {}", id);

    return convertDogEntityToOData(
        dogRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such dog")));
  }

  @Override
  public List<DogOData> findAll() {
    log.info("DogODataService. Get all dogs from database");

    return convertDogListOfEntityToListOfOData(dogRepository.findAll());
  }

  @Override
  public void deleteById(final long id) {
    log.info("DogODataService. Delete dog by id");

    dogRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void save(final Object odataEntity) {
    log.info("DogODataService. Save dog {}", odataEntity);

    DogOData dogOData = (DogOData) odataEntity;

    Dog dog = convertDogODataToEntity(dogOData);
    User user = userRepository
        .findById(dogOData.getUserId())
        .orElseThrow(() -> new EntityNotFoundException("No user with such id"));
    dog.setOwner(user);
    dog.setBirthdate(now());
    dog.setPetType(DOG);

    dogRepository.save(dog);
  }

  @Override
  public DogOData getODataObject() {
    log.info("DogODataService. Get ODataObject");

    return new DogOData();
  }
}
