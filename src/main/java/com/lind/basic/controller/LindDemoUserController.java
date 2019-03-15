package com.lind.basic.controller;

import com.lind.basic.util.CommonUtils;
import com.lind.basic.util.ResponseUtils;
import javax.security.sasl.AuthenticationException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LindDemoUserController {
  public static final String PATH = "/lind-demo/user/";
  public static final String LOGIN = "/lind-auth/login";
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  UserDetailsService userDetailsService;
  @Autowired
  RedisTemplate<String, String> redisTemplate;
  @Autowired
  LindDemoUserRepository lindDemoUserRepository;

  @PostMapping(PATH + "add")
  public ResponseEntity<?> add(@Valid @RequestBody LindDemoUserModel lindDemoUserModel) {
    lindDemoUserRepository.save(lindDemoUserModel);
    return ResponseUtils.okMessage("操作成功");
  }

  @GetMapping(PATH + "get")
  public ResponseEntity<?> get() {
    return ResponseUtils.ok(lindDemoUserRepository.findAll());
  }

  @GetMapping(LOGIN)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(
      @RequestParam String username,
      @RequestParam String password) throws AuthenticationException {
    return ResponseEntity.ok(generateToken(username, password));
  }

  /**
   * 登陆与授权.
   *
   * @param username .
   * @param password .
   * @return
   */
  private String generateToken(String username, String password) {
    UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
    // Perform the security
    final Authentication authentication = authenticationManager.authenticate(upToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    // Reload password post-security so we can generate token
    final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    // 持久化的redis
    String token = CommonUtils.encrypt(userDetails.getUsername());
    redisTemplate.opsForValue().set(token, userDetails.getUsername());
    return token;
  }
}
