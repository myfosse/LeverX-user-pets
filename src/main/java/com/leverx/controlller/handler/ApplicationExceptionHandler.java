package com.leverx.controlller.handler;

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
  protected ResponseEntity<EntityNotFoundException> handleEntityNotFoundException(
      final EntityNotFoundException exception) {
    return new ResponseEntity<>(exception, NOT_FOUND);
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

    return ResponseEntity.badRequest()
        .header(new HttpHeaders().toString())
        .body(new MessageResponse(stringBuffer.toString()));
  }
}
