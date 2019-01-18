package com.lind.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Spring Session，禁用了传统的session，将session放到了Redis中，可以在分布式的多个服务中找到用户的session.
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  /**
   * redis 配置.
   */
  @Bean
  public RedisTemplate redisTemplate() {
    RedisTemplate redisTemplate = new RedisTemplate();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    return redisTemplate;
  }
}
