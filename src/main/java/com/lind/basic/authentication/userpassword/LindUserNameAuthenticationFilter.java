package com.lind.basic.authentication.userpassword;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/**
 * 拦截请求，获取认证需要的数据，并传递给Provider step1.
 */
public class LindUserNameAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  public LindUserNameAuthenticationFilter() {

    super(new AntPathRequestMatcher("/login", "GET"));
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if (username == null) {
      throw new InternalAuthenticationServiceException("Failed to get the username");
    }

    if (password == null) {
      throw new InternalAuthenticationServiceException("Failed to get the password");
    }

    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
        username, password);

    authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

    // 向request上下文写授权信息，需要在provider时进行校验它的合法性
    return this.getAuthenticationManager().authenticate(authRequest);
  }
}
