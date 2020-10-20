package com.cplier.forum;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EncoderTest {

  @Test
  void encodePassword() {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    assertNotNull(passwordEncoder.encode("123"));
  }
}
