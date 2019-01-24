package com.lind.basic.controller;

import com.google.common.collect.ImmutableMap;
import com.lind.basic.authentication.SimpleTokenHelper;
import com.lind.basic.exception.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LindDemo {
  public static final String PATH = "/lind-demo";
  public static final String HELLO401 = PATH + "/hello401";
  public static final String HELLO400 = PATH + "/hello400";
  public static final String HELLO200 = PATH + "/hello200";


  @Autowired
  SimpleTokenHelper simpleTokenHelper;

  @GetMapping(HELLO401)
  public String hello401() {
    simpleTokenHelper.isLogin("zzl");
    return "hello";
  }

  @GetMapping(HELLO400)
  public void hello400() {
    throw Exceptions.badRequestParams("参数问题");
  }

  @GetMapping(HELLO200)
  public String hello200() {
    String token = simpleTokenHelper.writeToken(ImmutableMap.of("id", 1, "name", "zzl"));
    simpleTokenHelper.isLogin(token);
    return "hello";
  }
}
