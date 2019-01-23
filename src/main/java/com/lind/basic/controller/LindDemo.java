package com.lind.basic.controller;

import com.lind.basic.authentication.SimpleTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lind-demo")
public class LindDemo {
  @Autowired
  SimpleTokenHelper simpleTokenHelper;

  @GetMapping("hello")
  public String hello() {
    simpleTokenHelper.isLogin("zzl");
    return "hello";
  }
}
