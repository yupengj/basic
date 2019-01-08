package com.lind.basic.mq;

import com.lind.basic.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class MqTest extends BaseTest {
  @Autowired
  Publisher publisher;

  @Test
  public void publisherTest() {
    publisher.publish("hello lind");
  }
}
