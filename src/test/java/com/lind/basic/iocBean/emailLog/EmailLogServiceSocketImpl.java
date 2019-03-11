package com.lind.basic.iocBean.emailLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
@Component()
public class EmailLogServiceSocketImpl implements EmailLogService {
  private static final Logger logger = LoggerFactory.getLogger(EmailLogServiceSocketImpl.class);

  @Override
  public void send(String email, String message) {
    Assert.notNull(email, "email must not be null!");
    logger.info("send email2:{},message:{}", email, message);
  }
}
