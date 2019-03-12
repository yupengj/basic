package com.lind.basic.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.basic.enums.LindStatus;
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
  @Autowired
  ObjectMapper objectMapper;
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
            get(LindDemoController.HELLO200)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(documentationResultHandler);

  }

  @Test
  public void postTest() throws Exception {
    mockMvc
        .perform(
            post(LindDemoController.POSTDO)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("zzl"))
        .andExpect(status().isOk()).andDo(document("post-request"));
  }

  @Test
  public void getHttpError() throws Exception {
    mockMvc
        .perform(
            get(LindDemoController.GET_HTTP_ERROR)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is(400));

  }

  @Test
  public void getError() throws Exception {
    mockMvc
        .perform(
            get(LindDemoController.GET_ERROR)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

  }

  /**
   * 成功请求.
   */
  @Test
  public void postDataSuccess() throws Exception {
    mockMvc
        .perform(
            post(LindDemoController.POST_DATA)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(
                    UserDto.builder()
                        .name("zzl")
                        .status(LindStatus.NORMAL)
                        .build())))
        .andExpect(status().isOk())
        .andDo(print());
  }

  /**
   * 数据错误.
   */
  @Test
  public void postDataError() throws Exception {
    /**
     * Body = {"status":400,"message":"人员需要是本人，不能是家属","data":{}}
     */
    mockMvc
        .perform(
            post(LindDemoController.POST_DATA)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(
                    UserDto.builder()
                        .name("zhz")
                        .status(LindStatus.NORMAL)
                        .build())))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(400))
        .andDo(print());
  }

  /**
   * http错误.
   *
   */
  @Test
  public void postDataHttpError() throws Exception {
    /**
     * Body = {"status":400,"method":"POST","path":"/lind-demo/data","extra":null,
     * "errors":[{"code":null,"value":null,"message":"人员非法"}]}
     */
    mockMvc
        .perform(
            post(LindDemoController.POST_DATA)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(
                    UserDto.builder()
                        .name("lr")
                        .status(LindStatus.NORMAL)
                        .build())))
        .andExpect(status().is(400))
        .andExpect(jsonPath("$.status").value(400))
        .andDo(print());
  }
}
