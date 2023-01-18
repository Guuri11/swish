package com.swish.app.exception;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(final String id) {

    super("Could not find user " + id);
  }
}
