package com.lind.basic.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class WebTestClientTest {
  @Autowired
  WebTestClient webClient;


  @Test
  public void get() {
    webClient.get().uri(LindDemoController.GETDO)
        .exchange().expectStatus().isOk();
  }
}
