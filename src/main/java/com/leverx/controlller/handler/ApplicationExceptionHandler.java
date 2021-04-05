package com.leverx.controlller.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<EntityNotFoundException> handleEntityNotFoundException(
      final EntityNotFoundException exception) {
    return new ResponseEntity<>(exception, NOT_FOUND);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<IllegalArgumentException> handleIllegalArgumentException(
      final IllegalArgumentException exception) {
    return new ResponseEntity<>(exception, BAD_REQUEST);
  }
}
