package com.lind.basic.util;

import java.time.LocalDateTime;
import org.junit.Test;
import org.springframework.scheduling.annotation.Async;

public class AsyncTest {

  @Test
  public void testShareObj() throws Exception {
    System.out.println("main start:" + Thread.currentThread().getId() + LocalDateTime.now());
    print();
    System.out.println("main end:" + Thread.currentThread().getId() + LocalDateTime.now());
  }

  @Async
  void print() throws Exception {
    System.out.println("async.print:" + Thread.currentThread().getId() + LocalDateTime.now());
    Thread.sleep(5000L);
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
    Thread.sleep(5000);

  }
}


