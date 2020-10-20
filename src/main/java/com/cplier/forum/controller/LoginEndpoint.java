package com.cplier.forum.controller;

import com.cplier.forum.payload.LoginRequest;
import com.cplier.forum.payload.TokenResp;
import com.cplier.forum.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginEndpoint {

  @Resource private AuthenticationManager authenticationManager;
  @Resource private TokenService tokenService;

  @PostMapping
  public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequest loginRequest) {
    UsernamePasswordAuthenticationToken login = loginRequest.converter();
    try {
      Authentication authentication = authenticationManager.authenticate(login);
      String token = tokenService.generateToken(authentication);
      return ResponseEntity.ok(new TokenResp(token, "Bearer"));
    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
