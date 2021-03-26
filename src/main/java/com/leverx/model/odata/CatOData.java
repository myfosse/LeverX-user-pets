package com.leverx.model.odata;

import static org.apache.olingo.odata2.api.annotation.edm.EdmType.BOOLEAN;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;

import static com.leverx.constant.odata.ODataModelConstants.ENTITY_CONTAINER;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_NAME_CAT;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_SET_NAME_CATS;
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
@EdmEntitySet(name = ENTITY_SET_NAME_CATS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_CAT, namespace = ODATA_NAMESPACE)
public class CatOData extends PetOData {

  @EdmProperty(type = INT64)
  private Long userId;

  @EdmProperty(type = BOOLEAN)
  private Boolean bold;

  @Builder(builderMethodName = "catBuilder")
  public CatOData(
      final long id,
      final String petType,
      final String name,
      final Date birthdate,
      final UserOData owner,
      final long userId,
      final boolean bold) {
    super(id, petType, name, birthdate, owner);
    this.userId = userId;
    this.bold = bold;
  }
}
