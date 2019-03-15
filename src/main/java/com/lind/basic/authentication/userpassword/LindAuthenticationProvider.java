package com.lind.basic.authentication.userpassword;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 认证的提供者，实现检查签名和授权的功能.
 */
@Component
@Slf4j
public class LindAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  @Autowired
  UserDetailsService userDetailsService;
  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  RedisTemplate<String, String> redisTemplate;
  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * 校验密码有效性 step3.
   */
  @Override
  protected void additionalAuthenticationChecks(
      UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
    if (authentication.getCredentials() == null) {
      logger.debug("Authentication failed: no credentials provided");

      throw new BadCredentialsException(messages.getMessage(
          "AbstractUserDetailsAuthenticationProvider.badCredentials",
          "Bad credentials"));
    }
    // 当前输入的密码(从filter向下传递的变量)与数据库的密码比较
    String presentedPassword = authentication.getCredentials().toString();
    if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
      logger.debug("Authentication failed: password does not match stored value");

      throw new BadCredentialsException(messages.getMessage(
          "AbstractUserDetailsAuthenticationProvider.badCredentials",
          "Bad credentials"));
    }
  }

  /**
   * 获取用户 step2.
   */
  @Override
  protected UserDetails retrieveUser(
      String username, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
    UserDetails loadedUser = userDetailsService.loadUserByUsername(username);
    if (loadedUser == null) {
      throw new InternalAuthenticationServiceException(
          "UserDetailsService returned null, which is an interface contract violation");
    }
    return loadedUser;
  }

  /**
   * 授权持久化 step4.
   */
  @Override
  protected Authentication createSuccessAuthentication(
      Object principal, Authentication authentication, UserDetails user) {

    return super.createSuccessAuthentication(principal, authentication, user);
  }
}
