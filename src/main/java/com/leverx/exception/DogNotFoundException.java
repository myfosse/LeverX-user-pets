package com.leverx.exception;

/** @author Andrei Yahorau */
public class DogNotFoundException extends RuntimeException {

  public DogNotFoundException() {}

  public DogNotFoundException(final String errorMessage) {
    super(errorMessage);
  }
}
