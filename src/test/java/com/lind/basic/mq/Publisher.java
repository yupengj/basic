package com.lind.basic.mq;

import com.lind.basic.mock.AmqpConfigMock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
  @Autowired
  private RabbitTemplate rabbitTemplate;

  /**
   * 发送拨打电话消息.
   */
  public void publish(String message) {
    try {
      rabbitTemplate
          .convertAndSend(AmqpConfigMock.LIND_EXCHANGE, AmqpConfigMock.LIND_QUEUE_ROUTEKEY,
              message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 发布广播消息.
   *
   * @param message .
   */
  public void fanoutPublish(String message) {
    try {
      rabbitTemplate.convertAndSend(AmqpConfigMock.LIND_FANOUT_EXCHANGE, null, "广播消息");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

