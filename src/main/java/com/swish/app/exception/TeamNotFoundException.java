package com.swish.app.exception;

public class TeamNotFoundException extends RuntimeException {

  public TeamNotFoundException(final Long id) {

    super("Could not find team " + id);
  }
}
