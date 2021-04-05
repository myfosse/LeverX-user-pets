package com.leverx.service.odata.impl;

import static java.time.LocalDate.now;
import static java.util.Collections.emptyList;

import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

import static com.leverx.constant.odata.ODataModelConstants.ENTITY_SET_NAME_PETS;
import static com.leverx.converter.odata.UserConverterOData.convertUserEntityToOData;
import static com.leverx.converter.odata.UserConverterOData.convertUserListOfEntityToListOfOData;
import static com.leverx.converter.odata.UserConverterOData.convertUserODataToEntity;
import static com.leverx.model.entity.ERole.ROLE_USER;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.model.entity.User;
import com.leverx.model.odata.UserOData;
import com.leverx.repository.PetRepository;
import com.leverx.repository.UserRepository;
import com.leverx.service.odata.UserODataService;

import lombok.extern.slf4j.Slf4j;

/** @author Andrei Yahorau */
@Service
@Slf4j
public class UserODataServiceImpl implements UserODataService {

  private final UserRepository userRepository;
  private final PetRepository petRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserODataServiceImpl(
      final UserRepository userRepository,
      final PetRepository petRepository,
      final PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.petRepository = petRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Object getRelatedData(final Object sourceData, final String targetEntityName)
      throws ODataNotFoundException {
    log.info("Get related data");

    UserOData user = (UserOData) sourceData;
    if (ENTITY_SET_NAME_PETS.equals(targetEntityName)) {
      return user.getPets();
    }
    throw new ODataNotFoundException(ENTITY);
  }

  @Override
  public UserOData findById(final long id) {
    log.info("Find user in database by id: {}", id);

    return convertUserEntityToOData(
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no such user")));
  }

  @Override
  public List<UserOData> findAll() {
    log.info("Get all users from database");

    return convertUserListOfEntityToListOfOData(userRepository.findAll());
  }

  @Override
  @Transactional
  public void deleteById(final long id) {
    log.info("Delete user by id");

    petRepository.removeAllOwnerIdReference(id);
    userRepository.deleteById(id);
  }

  @Override
  public void save(final UserOData odataEntity) {
    log.info("Save new user {}", odataEntity);

    User user = convertUserODataToEntity(odataEntity);

    String password = passwordEncoder.encode(user.getPassword());

    user.setPassword(password);
    user.setBirthdate(now());
    user.setPets(emptyList());
    user.setRole(ROLE_USER);

    userRepository.save(user);
  }

  @Override
  public UserOData getODataObject() {
    log.info("Get ODataObject");

    return new UserOData();
  }
}
