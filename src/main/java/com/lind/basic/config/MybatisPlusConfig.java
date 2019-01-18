package com.lind.basic.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.lind.basic.entity.CreateUpdateTimeInterceptor;
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
  public CreateUpdateTimeInterceptor timeFullInterceptor() {
    return new CreateUpdateTimeInterceptor();
  }

  /**
   * 乐观锁插件.
   */
  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {
    return new OptimisticLockerInterceptor();
  }

  /**
   * 逻辑删除，更新等拦截.
   *
   * @return
   */
  @Bean
  public ISqlInjector sqlInjector() {
    return new LogicSqlInjector();
  }
}
