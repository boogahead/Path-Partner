package com.ssafy.enjoytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.ssafy.enjoytrip", exclude = SecurityAutoConfiguration.class)
public class EnjoyTripSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(EnjoyTripSpringApplication.class, args);
  }

}
