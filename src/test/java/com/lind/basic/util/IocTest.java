package com.lind.basic.util;

import com.lind.basic.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Config {
  @Value("${author}")
  String auth;

  public void hello() {
    System.out.println("author=" + auth);

  }
}

public class IocTest extends BaseTest {

  @Autowired
  Config config;

  @Test
  public void valueTest() {
    config.hello();
  }
}
