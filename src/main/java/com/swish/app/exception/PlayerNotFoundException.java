package com.swish.app.exception;

public class PlayerNotFoundException extends RuntimeException {

  public PlayerNotFoundException(final Long id) {

    super("Could not find player " + id);
  }
}
