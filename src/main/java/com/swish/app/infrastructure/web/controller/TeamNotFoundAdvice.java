package com.swish.app.infrastructure.web.controller;

import com.swish.app.exception.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TeamNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(TeamNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String teamNotFoundHandler(final TeamNotFoundException ex) {

    return ex.getMessage();
  }
}
