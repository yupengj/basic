package com.lind.basic.nosql;

import com.lind.basic.BaseTest;
import com.lind.basic.config.RedisLock;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RedisLockTest extends BaseTest {
  @Autowired
  RedisLock redisLock;

  @Test
  public void threadLock() {
    logger.info("hello init");
    try {
      long time = System.currentTimeMillis() + 10;
      redisLock.lock("product", String.valueOf(time));
      logger.info("hello start:{}", LocalDateTime.now());
      Thread.sleep(1000);
      redisLock.unlock("product", String.valueOf(time));
      logger.info("hello end:{}", LocalDateTime.now());
    } catch (Exception e) {
      logger.error("threadLock.error", e);
    }
  }
}
