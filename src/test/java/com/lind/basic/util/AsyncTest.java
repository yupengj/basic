package com.lind.basic.util;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest()
@EnableAsync
public class AsyncTest {

  /**
   * 方法强制为同步方法.
   */
  synchronized void queue() {
    System.out.println("printMessage synchronized result:" + LocalDateTime.now().toString());
  }

  @Test
  public void synchronizedTest() throws Exception {
    for (int i = 0; i < 5; i++) {
      new Thread(() -> queue()).start();
    }
    TimeUnit.MILLISECONDS.sleep(5000);

  }
}


