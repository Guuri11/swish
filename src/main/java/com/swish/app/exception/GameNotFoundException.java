package com.swish.app.exception;

public class GameNotFoundException extends RuntimeException {

  public GameNotFoundException(final Long id) {

    super("Could not find game " + id);
  }
}
