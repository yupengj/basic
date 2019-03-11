package com.lind.basic.iocBean;

import com.lind.basic.iocBean.emailLog.EmailLogService;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest()
public class BeanTest {
  @Resource(name = "emailLogServiceSocketImpl")
  EmailLogService socketEmail;
  @Autowired
  @Qualifier( "emailLogServiceHttpImpl")
  EmailLogService httpEmail;

  @Test
  public void testIoc2() {
    socketEmail.send("ok", "ok");
  }


  @Test
  public void testIoc1() {
    httpEmail.send("ok", "ok");
  }
}
