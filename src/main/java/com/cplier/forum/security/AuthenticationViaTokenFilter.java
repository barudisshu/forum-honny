package com.cplier.forum.security;

import com.cplier.forum.model.User;
import com.cplier.forum.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author ehcayen
 */
public class AuthenticationViaTokenFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final UserRepository userRepository;

  public AuthenticationViaTokenFilter(TokenService tokenService, UserRepository userRepository) {
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String token = authenticationToken(request);
    boolean valid = tokenService.isTokenValid(token);
    if (valid) {
      authorizationClient(token);
    }
    filterChain.doFilter(request, response);
  }

  /** 授权 */
  private void authorizationClient(String token) {
    Long userId = tokenService.getUserId(token);
    Optional<User> authenticatedUser = userRepository.findById(userId);
    if (authenticatedUser.isPresent()) {
      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(
              authenticatedUser, null, authenticatedUser.get().getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
  }

  /** 认证 */
  private String authenticationToken(HttpServletRequest request) {
    String headerAuthorization = request.getHeader("Authorization");
    if (headerAuthorization == null
        || headerAuthorization.isEmpty()
        || !headerAuthorization.startsWith("Bearer")
        || headerAuthorization.split(" ").length != 2) {
      return null;
    }

    return headerAuthorization.split(" ")[1];
  }
}
