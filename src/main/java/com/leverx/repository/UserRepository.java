package com.leverx.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leverx.model.entity.User;

/** @author Andrei Yahorau */
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(final String email);

  Optional<User> findByEmail(final String email);
}
