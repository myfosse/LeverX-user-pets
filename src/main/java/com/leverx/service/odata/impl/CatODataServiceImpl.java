package com.leverx.service.odata.impl;

import static java.time.LocalDate.now;

import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

import static com.leverx.constant.odata.ODataModelConstants.ENTITY_SET_NAME_USERS;
import static com.leverx.converter.odata.CatConverterOData.convertCatEntityToOData;
import static com.leverx.converter.odata.CatConverterOData.convertCatListOfEntityToListOfOData;
import static com.leverx.converter.odata.CatConverterOData.convertCatODataToEntity;
import static com.leverx.model.entity.EPetType.CAT;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.model.entity.Cat;
import com.leverx.model.entity.User;
import com.leverx.model.odata.CatOData;
import com.leverx.repository.CatRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.odata.CatODataService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class CatODataServiceImpl implements CatODataService {

  private final CatRepository catRepository;
  private final UserRepository userRepository;

  @Autowired
  public CatODataServiceImpl(final CatRepository catRepository,
                             final UserRepository userRepository) {
    this.catRepository = catRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Object getRelatedData(final Object sourceData, final String targetEntityName)
      throws ODataNotFoundException {
    log.info("Get related data");

    CatOData cat = (CatOData) sourceData;
    if (ENTITY_SET_NAME_USERS.equals(targetEntityName)) {
      return cat.getOwner();
    }
    throw new ODataNotFoundException(ENTITY);
  }

  @Override
  public CatOData findById(final long id) {
    log.info("Find cat in database by id: {}", id);

    return convertCatEntityToOData(
        catRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such cat")));
  }

  @Override
  public List<CatOData> findAll() {
    log.info("Get all cats from database");

    return convertCatListOfEntityToListOfOData(catRepository.findAll());
  }

  @Override
  public void deleteById(final long id) {
    log.info("Delete cat by id");

    catRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void save(final CatOData odataEntity) {
    log.info("Save cat");

    Cat cat = convertCatODataToEntity(odataEntity);
    User user = userRepository
            .findById(odataEntity.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("No user with such id"));
    cat.setOwner(user);
    cat.setBirthdate(now());
    cat.setPetType(CAT);

    catRepository.save(cat);
  }

  @Override
  public CatOData getODataObject() {
    log.info("Get ODataObject");

    return new CatOData();
  }
}
