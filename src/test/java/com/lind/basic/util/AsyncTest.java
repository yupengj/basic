package com.lind.basic.util;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@Component
class TestAsync {
  @Async
  void print() throws Exception {
    System.out.println("async.print:" + Thread.currentThread().getId() + LocalDateTime.now());
    TimeUnit.MILLISECONDS.sleep(5000);
    System.out.println("async.print:" + Thread.currentThread().getId() + LocalDateTime.now());

  }
}

@RunWith(SpringRunner.class)
@SpringBootTest()
@EnableAsync
public class AsyncTest {
  @Autowired
  TestAsync testAsync;

  @Test
  public void testShareObj() throws Exception {
    System.out.println("main start:" + Thread.currentThread().getId() + LocalDateTime.now());
    testAsync.print();
    System.out.println("main end:" + Thread.currentThread().getId() + LocalDateTime.now());
  }


  /**
   * 方法强制为同步方法.
   */
  synchronized void queue() {
    System.out.println("print synchronized result:" + LocalDateTime.now().toString());
  }

  @Test
  public void synchronizedTest() throws Exception {
    for (int i = 0; i < 5; i++) {
      new Thread(() -> queue()).start();
    }
    TimeUnit.MILLISECONDS.sleep(5000);

  }
}


