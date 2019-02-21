package com.lind.basic.iocBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class EmailLogServiceImpl implements EmailLogService {
  private static final Logger logger = LoggerFactory.getLogger(EmailLogServiceImpl.class);

  @Override
  public void send(String email, String message) {
    Assert.notNull(email, "email must not be null!");
    logger.info("send email:{},message:{}", email, message);
  }
}
