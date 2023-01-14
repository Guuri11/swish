package com.swish.app.exception;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(final Long id) {

    super("Could not find user " + id);
  }
}
