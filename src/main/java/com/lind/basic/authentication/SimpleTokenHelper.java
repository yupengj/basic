package com.lind.basic.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.basic.exception.Exceptions;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 实现简单的token处理.
 */
@Component
@Slf4j
public class SimpleTokenHelper {
  public static final String DISTRIBUTOR_TOKEN = "distributor_token:";
  @Autowired
  RedisTemplate<String, String> redisTemplate;
  @Autowired
  ObjectMapper objectMapper;

  /**
   * 写.
   *
   * @param entity .
   * @return
   */
  public <T> String writeToken(T entity) {
    try {
      String token = UUID.randomUUID().toString();
      redisTemplate.opsForValue().set(DISTRIBUTOR_TOKEN + token,
          objectMapper.writeValueAsString(entity));
      this.redisTemplate.expire(DISTRIBUTOR_TOKEN + token, 1, TimeUnit.DAYS);
      return token;
    } catch (Exception ex) {
      logger.error("AuthHelper.writeToken.error", ex);
      return "token error.";
    }
  }

  /**
   * 读.
   *
   * @param token .
   * @return
   */
  public Object readToken(String token) {
    try {
      return objectMapper.readValue(
          redisTemplate.opsForValue().get(DISTRIBUTOR_TOKEN + token), Object.class);
    } catch (Exception ex) {
      logger.error("AuthHelper.readToken.error", ex);
      return null;
    }
  }

  /**
   * 是否登陆.
   */
  public void isLogin(String token) {
    String key = DISTRIBUTOR_TOKEN + token;
    Boolean result = redisTemplate.hasKey(key);
    if (result == null || !result) {
      throw Exceptions.unauthorized("token无效!");
    }
  }
}
