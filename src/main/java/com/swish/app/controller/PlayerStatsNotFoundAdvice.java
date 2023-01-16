package com.swish.app.controller;

import com.swish.app.exception.PlayerStatsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PlayerStatsNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(PlayerStatsNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String playerNotFoundHandler(final PlayerStatsNotFoundException ex) {

    return ex.getMessage();
  }
}
