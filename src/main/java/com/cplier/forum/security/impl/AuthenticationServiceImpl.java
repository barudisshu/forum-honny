package com.cplier.forum.security.impl;

import com.cplier.forum.model.User;
import com.cplier.forum.repository.UserRepository;
import com.cplier.forum.security.AuthenticationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/** @author ehcayen */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository userRepository;

  public AuthenticationServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<User> userOption = userRepository.findByUsername(username);
    return userOption.orElseThrow(() -> new UsernameNotFoundException("username not found"));
  }
}
