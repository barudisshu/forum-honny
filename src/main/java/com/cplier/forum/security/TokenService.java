package com.cplier.forum.security;

import org.springframework.security.core.Authentication;

/** @author ehcayen */
public interface TokenService {
  /** 生成JWT token */
  String generateToken(Authentication authentication);

  /** 校验token是否合法 */
  boolean isTokenValid(String token);

  /** 提取用户ID */
  Long getUserId(String token);
}
