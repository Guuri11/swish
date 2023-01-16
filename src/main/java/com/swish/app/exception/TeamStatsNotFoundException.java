package com.swish.app.exception;

public class TeamStatsNotFoundException extends RuntimeException {

  public TeamStatsNotFoundException(final Long id) {

    super("Could not find team stats " + id);
  }
}
