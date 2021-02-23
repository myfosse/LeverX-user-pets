package com.leverx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leverx.entity.User;

/** @author Andrei Yahorau */
public interface UserRepository extends JpaRepository<User, Long> {}
