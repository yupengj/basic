package com.lind.basic.iocBean;

import javax.annotation.Resource;
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
  @Resource(name = "email-log2")
  EmailLogService logService2;
  @Resource(name = "email-log1")
  EmailLogService logService1;

  @Test
  public void testIoc2() {
    logService2.send("ok", "ok");
  }


  @Test
  public void testIoc1() {
    logService1.send("ok", "ok");
  }
}
