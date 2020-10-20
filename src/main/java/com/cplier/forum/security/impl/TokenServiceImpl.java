package com.cplier.forum.security.impl;

import com.cplier.forum.model.User;
import com.cplier.forum.security.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

  @Value("${forum.issuer: forum}")
  private String issuer;

  @Value("${forum.expiration: 30000}")
  private Long expiration;

  @Value("${forum.secret: s.AhD0ohRmlZXRymuBiCIE1SJW}")
  private String secret;

  @Override
  public String generateToken(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    Date expiredAt = new Date(System.currentTimeMillis() + expiration);
    return Jwts.builder()
        .setIssuer(issuer)
        .setSubject(user.getUserId().toString())
        .setIssuedAt(expiredAt)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  @Override
  public boolean isTokenValid(String token) {
    try {
      Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public Long getUserId(String token) {
    Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    return Long.parseLong(body.getSubject());
  }
}
