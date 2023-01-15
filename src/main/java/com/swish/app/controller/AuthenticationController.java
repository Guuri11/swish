package com.swish.app.controller;

import com.swish.app.request.AuthenticationRequest;
import com.swish.app.request.RegisterRequest;
import com.swish.app.response.AuthenticationResponse;
import com.swish.app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody final RegisterRequest request
  ) {

    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody final AuthenticationRequest request
  ) {

    return ResponseEntity.ok(service.authenticate(request));
  }


}