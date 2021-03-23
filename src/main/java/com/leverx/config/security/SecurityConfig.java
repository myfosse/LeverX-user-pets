package com.leverx.config.security;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import static com.leverx.constant.controller.ControllerConstant.ENDPOINT_AUTH;
import static com.leverx.constant.controller.ControllerConstant.ENDPOINT_CATS;
import static com.leverx.constant.controller.ControllerConstant.ENDPOINT_DOGS;
import static com.leverx.constant.controller.ControllerConstant.ENDPOINT_ODATA;
import static com.leverx.constant.controller.ControllerConstant.ENDPOINT_PETS;
import static com.leverx.constant.controller.ControllerConstant.ENDPOINT_USERS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.leverx.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsServiceImpl userDetailsService;

  @Autowired
  public SecurityConfig(final UserDetailsServiceImpl userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(final AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder
        .inMemoryAuthentication()
        .withUser("admin")
        .password(passwordEncoder().encode("adminPassword2021."))
        .authorities("ROLE_USER", "ROLE_ADMIN");
    authenticationManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(ENDPOINT_AUTH)
            .anonymous()
        .antMatchers(GET,
            ENDPOINT_CATS + "/**",
            ENDPOINT_DOGS + "/**",
            ENDPOINT_PETS + "/**",
            ENDPOINT_USERS+ "/**",
            ENDPOINT_ODATA + "/**")
            .hasAuthority("ROLE_USER")
        .antMatchers(POST,
            ENDPOINT_CATS + "/**",
            ENDPOINT_DOGS + "/**",
            ENDPOINT_PETS + "/**")
        .hasAuthority("ROLE_USER")
        .antMatchers(PUT,
            ENDPOINT_CATS + "/**",
            ENDPOINT_DOGS + "/**",
            ENDPOINT_PETS + "/**")
        .hasAuthority("ROLE_USER")
        .antMatchers(DELETE,
            ENDPOINT_CATS + "/**",
            ENDPOINT_DOGS + "/**",
            ENDPOINT_PETS + "/**")
        .hasAuthority("ROLE_USER")
        .antMatchers(
            POST,
            ENDPOINT_USERS + "/**")
            .hasAuthority("ROLE_ADMIN")
        .antMatchers(
            PUT,
            ENDPOINT_USERS + "/**")
        .hasAuthority("ROLE_ADMIN")
        .antMatchers(
            DELETE,
            ENDPOINT_USERS + "/**")
        .hasAuthority("ROLE_ADMIN")
        .and()
            .httpBasic()
        .and()
            .logout()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(STATELESS)
        .and()
            .csrf()
            .disable();
  }
}
