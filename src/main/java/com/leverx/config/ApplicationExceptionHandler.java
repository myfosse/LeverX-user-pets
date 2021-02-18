package com.leverx.config;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.leverx.payload.response.MessageResponse;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<?> handleThereIsNoSuchUserException() {
    return new ResponseEntity<>(
        new EntityNotFoundException("There is no such entity"), NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

    StringBuffer stringBuffer = new StringBuffer();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(fieldError -> stringBuffer.append(fieldError.getDefaultMessage()).append("\n"));

    return new ResponseEntity<>(
            new MessageResponse(stringBuffer.toString()),
            new HttpHeaders(),
            BAD_REQUEST);
  }
}
