package com.lind.basic.iocBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest()
public class BeanTest {
  @Autowired
  EmailLogService logService;

  @Test
  public void testIoc() {
    logService.send("ok", "ok");
  }
}
