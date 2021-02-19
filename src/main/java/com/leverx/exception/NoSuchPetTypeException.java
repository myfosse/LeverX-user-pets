package com.leverx.exception;

/** @author Andrei Yahorau */
public class NoSuchPetTypeException extends RuntimeException {

  public NoSuchPetTypeException() {}

  public NoSuchPetTypeException(final String errorMessage) {
    super(errorMessage);
  }
}
