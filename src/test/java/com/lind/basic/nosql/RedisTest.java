package com.lind.basic.nosql;

import com.lind.basic.BaseTest;
import com.lind.basic.config.JedisLock;
import com.lind.basic.config.RedisLock;
import java.time.LocalDateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest extends BaseTest {

  @Autowired
  RedisTemplate redisTemplate;
  @Autowired
  RedisLock redisLock;
  @Autowired
  JedisLock jedisLock;

  @Test
  public void setTest() throws Exception {

    redisTemplate.opsForValue().set("ok", "test");
    System.out.println(
        "setTest:" + redisTemplate.opsForValue().get("ok")
    );
  }

  /**
   * 方法强制为同步方法.
   */
  void queue() {
    String value = String.valueOf(System.currentTimeMillis() + 5);
    redisLock.lock("ok", value);
    System.out.println("print synchronized result:" + LocalDateTime.now().toString()
        + ",id:"
        + Thread.currentThread().getId());
    redisLock.unlock("ok", value);
  }

  @Test
  public void distributeLock() throws Exception {
    new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        queue();
      }
    }).start();
  }

  void queue2() {
    if (jedisLock.tryLock("lind", "test")) {
      for (int i = 0; i < 5; i++) {
        System.out.println("print synchronized result:" + LocalDateTime.now().toString()
            + ",id:"
            + Thread.currentThread().getId());
      }
      jedisLock.releaseLock("lind", "test");
    }
  }


  @Test
  public void distributeLock2() {

    new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        queue2();
      }
    }).start();
  }
}
