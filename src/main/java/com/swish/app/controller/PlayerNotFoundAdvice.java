package com.swish.app.controller;

import com.swish.app.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PlayerNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(PlayerNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String playerNotFoundHandler(final PlayerNotFoundException ex) {

    return ex.getMessage();
  }
}
