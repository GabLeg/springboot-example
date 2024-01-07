package com.example.springbootexample.api.controllers.exceptions;

public class ChuckNorrisException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "Chuck Norris stopped us !";

  public ChuckNorrisException() {
    super(DEFAULT_MESSAGE);
  }
}
