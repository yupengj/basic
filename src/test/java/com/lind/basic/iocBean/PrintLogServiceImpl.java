package com.lind.basic.iocBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintLogServiceImpl implements PrintLogService {
  private static final Logger logger = LoggerFactory.getLogger(LogService.class);

  @Override
  public void print(String message) {
    logger.info("print:{}", message);
  }
}
