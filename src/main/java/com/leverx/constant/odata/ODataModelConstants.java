package com.leverx.constant.odata;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class ODataModelConstants {

  public static final String ENTITY_SET_NAME_USERS = "Users";
  public static final String ENTITY_SET_NAME_PETS = "Pets";
  public static final String ENTITY_SET_NAME_CATS = "Cats";
  public static final String ENTITY_SET_NAME_DOGS = "Dogs";

  public static final String ENTITY_NAME_USER = "User";
  public static final String ENTITY_NAME_PET = "Pet";
  public static final String ENTITY_NAME_CAT = "Cat";
  public static final String ENTITY_NAME_DOG = "Dog";

  public static final String ODATA_NAMESPACE = "UserPetsOData";

  public static final String ASSOCIATION_USER_PET = "User_Pets_Pet_User";

  public static final String ODATA_ROLE_PET = "Pet_User";
  public static final String ODATA_ROLE_USER = "User_Pets";

  public static final String ENTITY_CONTAINER = "UserPetsODataEntityContainer";
}
