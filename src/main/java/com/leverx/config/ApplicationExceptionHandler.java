package com.leverx.config;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.leverx.exception.CatNotFoundException;
import com.leverx.exception.DogNotFoundException;
import com.leverx.exception.NoSuchPetTypeException;
import com.leverx.exception.PetNotFoundException;
import com.leverx.exception.UserNotFoundException;
import com.leverx.payload.response.MessageResponse;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  protected ResponseEntity<UserNotFoundException> handleThereIsNoSuchUserException() {
    return new ResponseEntity<>(
            new UserNotFoundException("There is no such user"),
            NOT_FOUND);
  }

  @ExceptionHandler(PetNotFoundException.class)
  protected ResponseEntity<PetNotFoundException> handleThereIsNoSuchPetException() {
    return new ResponseEntity<>(
            new PetNotFoundException("There is no such pet"),
            NOT_FOUND);
  }

  @ExceptionHandler(CatNotFoundException.class)
  protected ResponseEntity<CatNotFoundException> handleThereIsNoSuchCatException() {
    return new ResponseEntity<>(
            new CatNotFoundException("There is no such cat"),
            NOT_FOUND);
  }

  @ExceptionHandler(DogNotFoundException.class)
  protected ResponseEntity<DogNotFoundException> handleThereIsNoSuchDogException() {
    return new ResponseEntity<>(
            new DogNotFoundException("There is no such dog"),
            NOT_FOUND);
  }

  @ExceptionHandler(NoSuchPetTypeException.class)
  protected ResponseEntity<NoSuchPetTypeException> handleThereIsNoSuchPetTypeException() {
    return new ResponseEntity<>(
            new NoSuchPetTypeException("There is no such pet type"),
            NOT_FOUND);
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

    return ResponseEntity
            .badRequest()
            .header(new HttpHeaders().toString())
            .body(new MessageResponse(stringBuffer.toString()));
  }
}
