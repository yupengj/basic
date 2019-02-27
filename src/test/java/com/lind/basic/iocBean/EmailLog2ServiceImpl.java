package com.lind.basic.iocBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
@Component
@Primary
public class EmailLog2ServiceImpl implements EmailLogService {
  private static final Logger logger = LoggerFactory.getLogger(EmailLog2ServiceImpl.class);

  @Override
  public void send(String email, String message) {
    Assert.notNull(email, "email must not be null!");
    logger.info("send email2:{},message:{}", email, message);
  }
}
