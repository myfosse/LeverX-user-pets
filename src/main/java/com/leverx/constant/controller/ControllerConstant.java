package com.leverx.constant.controller;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor(access = PRIVATE)
public final class ControllerConstant {

  public static final String PREFIX = "/api/v1";

  public static final String ENDPOINT_AUTH  = PREFIX + "/auth";
  public static final String ENDPOINT_CATS  = PREFIX + "/cats";
  public static final String ENDPOINT_DOGS  = PREFIX + "/dogs";
  public static final String ENDPOINT_PETS  = PREFIX + "/pets";
  public static final String ENDPOINT_USERS = PREFIX + "/users";
}
