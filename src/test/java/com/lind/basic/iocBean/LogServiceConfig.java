package com.lind.basic.iocBean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 下面作为LogService的行为修饰，可以在外部扩展它们，使LogService有更多的个性化行为.
 */
@Configuration
public class LogServiceConfig {

  /**
   * 扩展printLogService行为，直接影响到LogService对象，因为LogService依赖于PrintLogService.
   *
   * @return
   */
  @Bean
  public PrintLogService printLogService() {
    return new PrintLogServiceImpl();
  }

  @Bean
  public PrintLogService consolePrintLogService() {
    return new ConsolePrintLogService();
  }
}
