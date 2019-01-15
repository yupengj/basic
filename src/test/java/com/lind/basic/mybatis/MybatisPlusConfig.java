package com.lind.basic.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus 相关配置.
 * register mapper.
 */
@Configuration
@MapperScan("com.lind.basic.mybatis")
public class MybatisPlusConfig {

  /**
   * 分页插件.
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }

  /**
   * 建立与更新时间填充插件.
   *
   * @return
   */
  @Bean
  public TimeFullInterceptor timeFullInterceptor() {
    return new TimeFullInterceptor();
  }
}
