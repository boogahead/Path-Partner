package com.ssafy.pathpartner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.ssafy.pathpartner", exclude = SecurityAutoConfiguration.class)
public class EnjoyTripSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(EnjoyTripSpringApplication.class, args);
  }

}
