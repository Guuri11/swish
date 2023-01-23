package com.swish.app.infrastructure.web.controller;

import com.swish.app.exception.TeamStatsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TeamStatsNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(TeamStatsNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String teamStatsNotFoundHandler(final TeamStatsNotFoundException ex) {

    return ex.getMessage();
  }
}
