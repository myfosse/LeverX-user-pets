package com.leverx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class UserPetsApplication {

  public static void main(final String[] args) {
    SpringApplication.run(UserPetsApplication.class, args);
  }
}