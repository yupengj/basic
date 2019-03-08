package com.lind.basic.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class MockMvcTest {
  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void init() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(documentationConfiguration(this.restDocumentation))
        .alwaysDo(print())
        .build();

  }

  @Test
  public void getTest() throws Exception {
    RestDocumentationResultHandler documentationResultHandler = document("get-lind-demo");
    mockMvc
        .perform(
            get(LindDemo.HELLO200)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(documentationResultHandler);

  }

  @Test
  public void postTest() throws Exception {
    mockMvc
        .perform(
            post(LindDemo.POSTDO)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("zzl"))
        .andExpect(status().isOk()).andDo(document("post-request"));
  }

  @Test
  public void getHttpError() throws Exception {
    mockMvc
        .perform(
            get(LindDemo.GET_HTTP_ERROR)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is(400));

  }

  @Test
  public void getError() throws Exception {
    mockMvc
        .perform(
            get(LindDemo.GET_ERROR)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

  }
}
