package com.cplier.forum.config;

import com.cplier.forum.repository.UserRepository;
import com.cplier.forum.security.AuthenticationService;
import com.cplier.forum.security.AuthenticationViaTokenFilter;
import com.cplier.forum.security.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/** @author ehcayen */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource private AuthenticationService authenticationService;
  @Resource private TokenService tokenService;
  @Resource private UserRepository userRepository;

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/topic")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/topic/*")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/auth")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/actuator/*")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(
            new AuthenticationViaTokenFilter(tokenService, userRepository),
            UsernamePasswordAuthenticationFilter.class);
  }
}
