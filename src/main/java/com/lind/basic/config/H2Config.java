package com.lind.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * H2数据库初始化.
 */
@Configuration
@Profile("test")
public class H2Config {
  @Bean
  EmbeddedDatabaseFactoryBean dataSource() {
    EmbeddedDatabaseFactoryBean factoryBean = new EmbeddedDatabaseFactoryBean();
    factoryBean.setDatabaseType(EmbeddedDatabaseType.H2);
    factoryBean.setDatabaseName("test");
    factoryBean.setDatabasePopulator(new ResourceDatabasePopulator(
        new ClassPathResource("db/schema.sql")
    ));
    return factoryBean;
  }
}
