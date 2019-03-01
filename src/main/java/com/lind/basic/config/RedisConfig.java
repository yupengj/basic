package com.lind.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Spring Session，禁用了传统的session，将session放到了Redis中，可以在分布式的多个服务中找到用户的session.
 */
@Configuration
@EnableRedisHttpSession
public class RedisConfig {

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  /**
   * 统计bit位为1的总数
   *
   * @param key
   */
  public long bitCount(final String key) {
    return redisConnectionFactory.getConnection().bitCount(key.getBytes());
  }

  /**
   * redis 配置.
   */
  @Bean
  public RedisTemplate redisTemplate() {
    RedisTemplate redisTemplate = new RedisTemplate();
    RedisSerializer stringSerializer = new StringRedisSerializer();
    redisTemplate.setKeySerializer(stringSerializer);
    redisTemplate.setValueSerializer(stringSerializer);
    redisTemplate.setHashKeySerializer(stringSerializer);
    redisTemplate.setHashValueSerializer(stringSerializer);
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    return redisTemplate;
  }
}
