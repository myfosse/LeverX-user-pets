package com.leverx.exception;

/** @author Andrei Yahorau */
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException() {}

  public UserNotFoundException(final String errorMessage) {
    super(errorMessage);
  }
}
