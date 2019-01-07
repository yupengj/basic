package com.lind.basic.config;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 分布锁，锁被占用时返回false，成功返回true，没有自动重试功能.
 */
@Component
@Slf4j
public class RedisLock {

  private static final int MAX_TRY_COUNT = 10;
  private static final int TRY_INTERNAL = 10;

  @Autowired
  StringRedisTemplate redisTemplate;

  /**
   * 加锁.
   *
   * @param key   锁的key
   * @param value 当前时间戳+超时时间秒
   * @return
   */
  public boolean lock(String key, String value) {
    int tryCount = 0;
    try {
      while (true) {
        // 重试次数判断
        if (++tryCount >= MAX_TRY_COUNT) {
          return false;
        }
        // 如果没有线程访问锁就直接拿到锁
        if (Objects.equals(true, redisTemplate.opsForValue().setIfAbsent(key, value))) {
          return true;
        }
        // 拿到当前锁，去判断可用性
        String currentValue = redisTemplate.opsForValue().get(key);
        // 如果锁已经超时，去判断新锁有效性，如果没有超时，将不能获取正在使用的锁
        if (!StringUtils.isEmpty(currentValue)
            && Long.parseLong(currentValue) < System.currentTimeMillis()) {
          // 判断当前锁与之前设置的锁是否为同一把锁，因为value与currentValue在unlock之后,可能由不同进程线程去操作,
          // 如果进行lock方法时，没有执行unLock，那这个线程是不能拿到锁的
          String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
          if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
            return true;
          }
        }
        Thread.sleep(TRY_INTERNAL);
      }
    } catch (Exception e) {
      logger.error("redisLock.error", e);
    }
    return false;
  }

  /**
   * 解锁.
   *
   * @param key   .
   * @param value .
   */
  public void unlock(String key, String value) {
    try {
      String currentValue = redisTemplate.opsForValue().get(key);
      if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
        redisTemplate.opsForValue().getOperations().delete(key);
      }
    } catch (Exception e) {
      logger.error("redisUnlock.error", e);
    }
  }

}