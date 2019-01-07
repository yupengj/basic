package com.lind.basic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义的配置，直接映射成对象.
 * 出现的问题：在bootstrap.yml里不能映射，由application.yml里是可以映射的.
 */
@Configuration
@ConfigurationProperties(prefix = "author")
public class AuthorConfig {
  private String name;
  private String email;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
