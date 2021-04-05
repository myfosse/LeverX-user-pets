package com.leverx.model.odata;

import static org.apache.olingo.odata2.api.annotation.edm.EdmType.BOOLEAN;

import static com.leverx.constant.odata.ODataModelConstants.ENTITY_CONTAINER;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_NAME_DOG;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_SET_NAME_DOGS;
import static com.leverx.constant.odata.ODataModelConstants.ODATA_NAMESPACE;

import java.util.Date;

import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Data
@NoArgsConstructor
@EdmEntitySet(name = ENTITY_SET_NAME_DOGS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_DOG, namespace = ODATA_NAMESPACE)
public class DogOData extends PetOData {

  @EdmProperty(type = BOOLEAN)
  private Boolean guideDog;

  @Builder(builderMethodName = "dogBuilder")
  public DogOData(
      final long id,
      final String petType,
      final String name,
      final Date birthdate,
      final UserOData owner,
      final long userId,
      final boolean guideDog) {
    super(id, userId, petType, name, birthdate, owner);
    this.guideDog = guideDog;
  }
}
