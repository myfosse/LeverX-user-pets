package com.leverx.config.odata;

import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

import static com.leverx.config.context.AppContext.getApplicationContext;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_SET_NAME_CATS;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_SET_NAME_DOGS;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_SET_NAME_PETS;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_SET_NAME_USERS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.edm.EdmFunctionImport;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import com.leverx.service.odata.CatODataService;
import com.leverx.service.odata.DogODataService;
import com.leverx.service.odata.ODataService;
import com.leverx.service.odata.PetODataService;
import com.leverx.service.odata.UserODataService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ODataUserPetsSource implements DataSource {

  private final Map<String, ODataService> odataMap = new HashMap<>();

  public ODataUserPetsSource() {
    UserODataService userODataService =
        (UserODataService) getApplicationContext().getBean("userODataServiceImpl");
    PetODataService petODataService =
        (PetODataService) getApplicationContext().getBean("petODataServiceImpl");
    CatODataService catODataService =
        (CatODataService) getApplicationContext().getBean("catODataServiceImpl");
    DogODataService dogODataService =
        (DogODataService) getApplicationContext().getBean("dogODataServiceImpl");

    odataMap.put(ENTITY_SET_NAME_USERS, userODataService);
    odataMap.put(ENTITY_SET_NAME_PETS, petODataService);
    odataMap.put(ENTITY_SET_NAME_CATS, catODataService);
    odataMap.put(ENTITY_SET_NAME_DOGS, dogODataService);
  }

  @Override
  public List<?> readData(final EdmEntitySet entitySet)
      throws EdmException, ODataNotFoundException {
    log.info("ODataUserPetsSource. Read data");

    String entitySetName = entitySet.getName();

    if (odataMap.containsKey(entitySetName)) {
      return odataMap.get(entitySetName).findAll();
    }

    throw new ODataNotFoundException(ENTITY);
  }

  @Override
  public Object readData(final EdmEntitySet entitySet, final Map<String, Object> keys)
      throws ODataNotFoundException, EdmException {
    log.info("ODataUserPetsSource. Read data by id");

    String entitySetName = entitySet.getName();

    long firstLayerEntityId = (long) keys.get("Id");

    if (odataMap.containsKey(entitySetName)) {
      return odataMap.get(entitySetName).findById(firstLayerEntityId);
    }

    throw new ODataNotFoundException(ENTITY);
  }

  @Override
  public Object readData(
      final EdmFunctionImport function,
      final Map<String, Object> parameters,
      final Map<String, Object> keys)
      throws ODataNotImplementedException {
    throw new ODataNotImplementedException();
  }

  @Override
  public Object readRelatedData(
      final EdmEntitySet sourceEntitySet,
      final Object sourceData,
      final EdmEntitySet targetEntitySet,
      final Map<String, Object> targetKeys)
      throws EdmException, ODataNotFoundException {
    log.info("ODataUserPetsSource. Read related Data");

    String sourceEntityName = sourceEntitySet.getName();
    String targetEntityName = targetEntitySet.getName();

    return odataMap.get(sourceEntityName).getRelatedData(sourceData, targetEntityName);
  }

  @Override
  public BinaryData readBinaryData(final EdmEntitySet entitySet, final Object mediaLinkEntryData)
      throws ODataNotImplementedException {
    throw new ODataNotImplementedException();
  }

  @Override
  public Object newDataObject(final EdmEntitySet entitySet) throws EdmException {
    log.info("ODataUserPetsSource. New data object");

    String entitySetName = entitySet.getName();

    return odataMap.get(entitySetName).getODataObject();
  }

  @Override
  public void writeBinaryData(
      final EdmEntitySet entitySet, final Object mediaLinkEntryData, final BinaryData binaryData)
      throws ODataNotImplementedException {
    throw new ODataNotImplementedException();
  }

  @Override
  public void deleteData(final EdmEntitySet entitySet, final Map<String, Object> keys)
      throws EdmException {
    log.info("ODataUserPetsSource. Delete data by id");

    String entitySetName = entitySet.getName();

    long firstLayerEntityId = (long) keys.get("Id");

    if (odataMap.containsKey(entitySetName)) {
      odataMap.get(entitySetName).deleteById(firstLayerEntityId);
    }
  }

  @Override
  public void createData(final EdmEntitySet entitySet, final Object data) throws EdmException {
    log.info("ODataUserPetsSource. Create data object {}", data);

    String entitySetName = entitySet.getName();
    odataMap.get(entitySetName).save(data);
  }

  @Override
  public void deleteRelation(
      final EdmEntitySet sourceEntitySet,
      final Object sourceData,
      final EdmEntitySet targetEntitySet,
      final Map<String, Object> targetKeys)
      throws ODataNotImplementedException {
    throw new ODataNotImplementedException();
  }

  @Override
  public void writeRelation(
      final EdmEntitySet sourceEntitySet,
      final Object sourceData,
      final EdmEntitySet targetEntitySet,
      final Map<String, Object> targetKeys)
      throws ODataNotImplementedException {
    throw new ODataNotImplementedException();
  }
}
