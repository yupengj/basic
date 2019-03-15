package com.lind.basic.authentication.userpassword;

import com.lind.basic.authentication.userpassword.LindAuthenticationFailHandler;
import com.lind.basic.authentication.userpassword.LindAuthenticationProvider;
import com.lind.basic.authentication.userpassword.LindAuthenticationSuccessHandler;
import com.lind.basic.authentication.userpassword.LindUserNameAuthenticationFilter;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全配置.
 *
 * @EnableWebMvcSecurity 注解开启Spring Security的功能.
 * @EnableGlobalMethodSecurity 注解表示开启@PreAuthorize,@PostAuthorize,@Secured.
 */
@Profile("old")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  LindAuthenticationSuccessHandler lindAuthenticationSuccessHandler;

  @Autowired
  LindAuthenticationFailHandler lindAuthenticationFailHandler;
  @Autowired
  LindAuthenticationProvider lindAuthenticationProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/", "/index").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")//按路由授权
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/hello")//默认登录成功后跳转的页面
        .successHandler(lindAuthenticationSuccessHandler)
        .failureHandler(lindAuthenticationFailHandler)
        .permitAll()
        .and()
        .addFilterAt(lindAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .and()
        .logout()
        .permitAll();
  }

  /**
   * 自定义的Filter.
   *
   * @return
   */
  @Bean
  LindUserNameAuthenticationFilter lindAuthenticationFilter() {
    LindUserNameAuthenticationFilter lindUserNameAuthenticationFilter =
        new LindUserNameAuthenticationFilter();
    ProviderManager providerManager =
        new ProviderManager(Collections.singletonList(lindAuthenticationProvider));
    lindUserNameAuthenticationFilter.setAuthenticationManager(providerManager);
    lindUserNameAuthenticationFilter.setAuthenticationSuccessHandler(
        lindAuthenticationSuccessHandler);
    lindUserNameAuthenticationFilter.setAuthenticationFailureHandler(
        lindAuthenticationFailHandler);
    return lindUserNameAuthenticationFilter;
  }

  /**
   * 密码生成策略.
   *
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
