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
    webClient.get().uri(LindDemo.GETDO)
        .exchange().expectStatus().isOk();
  }

  @Test
  public void put() {
    webClient.put().uri("api/v1")
        .syncBody("zzl")
        .exchange()
        .expectStatus().isOk();
  }

  @Test
  public void post() {
    webClient.post()
        .uri(uriBuilder -> uriBuilder.path(LindDemo.POSTDO)
            .queryParam("mobileNumbers", "17694873618")
            .queryParam("mobileNumbers", "17694873618")
            .build())
        .syncBody("ok")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.data[1].mobileNumber").isEqualTo("17694873618");
  }
}
