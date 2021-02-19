package com.leverx.exception;

/** @author Andrei Yahorau */
public class CatNotFoundException extends RuntimeException {

  public CatNotFoundException() {}

  public CatNotFoundException(final String errorMessage) {
    super(errorMessage);
  }
}
