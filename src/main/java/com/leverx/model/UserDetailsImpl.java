package com.leverx.model;

import static java.util.Collections.singletonList;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leverx.model.entity.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** @author Andrey Egorov */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;

  private final Long id;

  private final String email;

  @JsonIgnore
  private final String password;

  private final Collection<? extends GrantedAuthority> authorities;

  public static UserDetailsImpl build(final User user) {
    List<GrantedAuthority> authorities =
        singletonList(new SimpleGrantedAuthority(user.getRole().name()));

    return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
