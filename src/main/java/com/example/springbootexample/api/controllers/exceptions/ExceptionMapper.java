package com.example.springbootexample.api.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = EntityNotFoundException.class)
  public ResponseEntity<Object> notFound(RuntimeException ex, WebRequest request) {
    ErrorResponse error = new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> globalReturn(RuntimeException ex, WebRequest request) {
    ErrorResponse error = new ErrorResponse(new Date(),
                                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                            ex.getMessage(),
                                            request.getDescription(false));
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
