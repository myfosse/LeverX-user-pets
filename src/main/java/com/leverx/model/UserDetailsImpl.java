package com.leverx.model;

import static java.util.Collections.singletonList;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leverx.entity.User;

import lombok.Getter;

/** @author Andrey Egorov */
@Getter
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;

  private final Long id;
  private final String email;
  @JsonIgnore private final String password;

  private final Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(
      final Long id,
      final String email,
      final String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
