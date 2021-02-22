package com.leverx.exception;

/** @author Andrei Yahorau */
public class PetNotFoundException extends RuntimeException {

  public PetNotFoundException() {}

  public PetNotFoundException(final String errorMessage) {
    super(errorMessage);
  }
}
