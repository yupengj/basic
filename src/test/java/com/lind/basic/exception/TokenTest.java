package com.lind.basic.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lind.basic.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;

public class TokenTest extends BaseTest {
  @Test
  public void simpleToken() throws Exception {
    mockMvc
        .perform(
            get("/lind-demo/hello")
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is(401))
        .andDo(print())
        .andExpect(jsonPath("$.errors[0].message").value("token无效!"));

  }
}
