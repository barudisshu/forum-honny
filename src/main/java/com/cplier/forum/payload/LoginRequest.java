package com.cplier.forum.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author ehcayen
 */
@Getter
@Setter
public class LoginRequest {
  private String username;
  private String password;

  public UsernamePasswordAuthenticationToken converter() {
    return new UsernamePasswordAuthenticationToken(username, password);
  }
}
