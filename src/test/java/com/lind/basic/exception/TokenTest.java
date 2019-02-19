package com.lind.basic.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lind.basic.BaseTest;
import com.lind.basic.controller.LindDemo;
import org.junit.Test;
import org.springframework.http.MediaType;

public class TokenTest extends BaseTest {
  @Test
  public void simple401() throws Exception {
    mockMvc
        .perform(
            get(LindDemo.HELLO401)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is(401))
        .andDo(print())
        .andExpect(jsonPath("$.errors[0].message").value("token无效!"));

  }

  @Test
  public void simple400() throws Exception {
    mockMvc
        .perform(
            get(LindDemo.HELLO400)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is(400))
        .andDo(print())
        .andExpect(jsonPath("$.errors[0].message").value("参数问题"));

  }

  @Test
  public void simple200() throws Exception {
    mockMvc
        .perform(
            get(LindDemo.HELLO200)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

  }


}
