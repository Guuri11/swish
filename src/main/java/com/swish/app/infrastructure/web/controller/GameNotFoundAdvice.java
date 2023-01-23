package com.swish.app.infrastructure.web.controller;

import com.swish.app.exception.GameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GameNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(GameNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String teamNotFoundHandler(final GameNotFoundException ex) {

    return ex.getMessage();
  }
}
