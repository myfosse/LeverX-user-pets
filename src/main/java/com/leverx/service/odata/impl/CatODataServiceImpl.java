package com.leverx.service.odata.impl;

import static java.time.LocalDate.now;

import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

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
import com.leverx.model.odata.UserOData;
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
    log.info("CatODataService. Get related data");

    throw new ODataNotFoundException(ENTITY);
  }

  @Override
  public CatOData findById(final long id) {
    log.info("CatODataService. Find cat in database by id: {}", id);

    return convertCatEntityToOData(
        catRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such cat")));
  }

  @Override
  public List<CatOData> findAll() {
    log.info("CatODataService. Get all cats from database");

    return convertCatListOfEntityToListOfOData(catRepository.findAll());
  }

  @Override
  public void deleteById(final long id) {
    log.info("CatODataService. Delete cat by id");

    catRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void save(final Object odataEntity) {
    log.info("CatODataService. Save cat");

    CatOData catOData = (CatOData) odataEntity;

    Cat cat = convertCatODataToEntity(catOData);
    User user = userRepository
            .findById(catOData.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("No user with such id"));
    cat.setOwner(user);
    cat.setBirthdate(now());
    cat.setPetType(CAT);

    catRepository.save(cat);
  }

  @Override
  public CatOData getODataObject() {
    log.info("CatODataService. Get ODataObject");

    return new CatOData();
  }
}
