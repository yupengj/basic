package com.lind.basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.lind.basic.controller")
public class BasicApplication {

  public static void main(String[] args) {
    SpringApplication.run(BasicApplication.class, args);
  }
}
