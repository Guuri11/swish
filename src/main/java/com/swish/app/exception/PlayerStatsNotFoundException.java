package com.swish.app.exception;

public class PlayerStatsNotFoundException extends RuntimeException {

  public PlayerStatsNotFoundException(final Long id) {

    super("Could not find player stats " + id);
  }
}
