package com.leverx.service.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leverx.model.UserDetailsImpl;
import com.leverx.model.entity.User;
import com.leverx.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/** @author Andrey Egorov */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
    log.info("UserDetailsService. Load user from database by email: {}", email);

    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException(
                    "UserDetailsService. User Not Found with email: " + email));

    return UserDetailsImpl.build(user);
  }
}
