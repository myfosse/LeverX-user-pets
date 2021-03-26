package com.leverx.model.odata;

import static org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity.ONE;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.DATE_TIME;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

import static com.leverx.constant.odata.ODataModelConstants.ASSOCIATION_USER_PET;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_CONTAINER;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_NAME_PET;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_NAME_USER;
import static com.leverx.constant.odata.ODataModelConstants.ENTITY_SET_NAME_PETS;
import static com.leverx.constant.odata.ODataModelConstants.ODATA_NAMESPACE;
import static com.leverx.constant.odata.ODataModelConstants.ODATA_ROLE_USER;

import java.util.Date;

import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EdmEntitySet(name = ENTITY_SET_NAME_PETS, container = ENTITY_CONTAINER)
@EdmEntityType(name = ENTITY_NAME_PET, namespace = ODATA_NAMESPACE)
public class PetOData {

  @EdmKey
  @EdmProperty(type = INT64)
  private long id;

  @EdmProperty(type = STRING)
  private String name;

  @EdmProperty(type = STRING)
  private String petType;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
  @JsonSerialize(using = DateSerializer.class)
  @EdmProperty(type = DATE_TIME)
  private Date birthdate;

  @EdmNavigationProperty(toMultiplicity = ONE,
      toType = UserOData.class,
      association = ASSOCIATION_USER_PET,
      toRole = ODATA_ROLE_USER,
      name = ENTITY_NAME_USER)
  private UserOData owner;
}
