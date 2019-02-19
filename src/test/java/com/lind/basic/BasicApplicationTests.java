package com.lind.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Profile("test")
@EnableAsync
public class BasicApplicationTests {

  @Test
  public void contextLoads() {
  }

}
