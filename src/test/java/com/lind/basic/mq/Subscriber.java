package com.lind.basic.mq;

import com.lind.basic.mock.AmqpConfigMock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Subscriber {

  /**
   * subscriber.
   *
   * @param data .
   */
  @RabbitListener(queues = AmqpConfigMock.LIND_DEAD_QUEUE)
  public void customerSign(String data) {
    try {

      logger.info("从队列拿到数据 ：{}", data);

    } catch (Exception ex) {
      logger.error("签约同步异常", ex);
    }
  }

  /**
   * lindqueue.
   *
   * @param data .
   */
  @RabbitListener(queues = AmqpConfigMock.LIND_QUEUE)
  public void lindQueue(String data) {
    try {

      logger.info("LIND_QUEUE从队列拿到数据 ：{}", data);

    } catch (Exception ex) {
      logger.error("LIND_QUEUE异常", ex);
    }
  }

  @RabbitListener(queues = "product1.queue")
  public void product1(String data) {
    System.out.println(data);
  }

  @RabbitListener(queues = "product2.queue")
  public void product2(String data) {
    System.out.println(data);
  }
}
