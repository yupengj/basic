package com.lind.basic.mock;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

/**
 * redis模拟.
 */
@Profile("test")
@Component
public class RedisServerMock {
  private RedisServer redisServer;

  @Value("localhost")
  private String redisHost;

  @Value("${random.int[58000,60000]}")
  private int redisPort;

  @Bean
  RedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setHostName(redisHost);
    factory.setPort(redisPort);
    factory.setUsePool(true);
    return factory;
  }


  @PostConstruct
  public void startRedis() throws IOException {
    redisServer = new RedisServer(redisPort);
    redisServer.start();
  }

  @PreDestroy
  public void stopRedis() {
    redisServer.stop();
  }
}
