package com.lind.basic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.basic.exception.Exceptions;
import com.lind.basic.util.ResponseUtils;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LindDemoController {
  public static final String PATH = "/lind-demo";
  public static final String HELLO401 = PATH + "/hello401";
  public static final String HELLO400 = PATH + "/hello400";
  public static final String HELLO200 = PATH + "/hello200";
  public static final String POSTDO = PATH + "/post-do";
  public static final String GETDO = PATH + "/get-do";
  public static final String GET_ERROR = PATH + "/get-error";
  public static final String GET_HTTP_ERROR = PATH + "/get-http-error";
  public static final String POST_DATA = PATH + "/data";

  @Autowired
  ObjectMapper objectMapper;
  String tokenHeader = "Authorization";

  /**
   * hello .
   *
   * @return
   */
  @GetMapping(HELLO401)
  public ResponseEntity<?> hello401() {
    return ResponseUtils.okMessage("hello");
  }

  /**
   * hello .
   *
   * @return
   */
  @GetMapping(HELLO400)
  public ResponseEntity<?> hello400() {
    throw Exceptions.badRequestParams("参数问题");
  }

  /**
   * hello .
   *
   * @return
   */
  @GetMapping(HELLO200)
  public ResponseEntity<?> hello200() throws Exception {

    return ResponseUtils.ok(true);
  }

  @GetMapping("/foo")
  void handleFoo(HttpServletResponse response) throws IOException {
    response.sendRedirect("foo2");
  }

  @GetMapping("/foo2")
  String handleFoo2() throws IOException {
    return "ok";
  }

  /**
   * get请求测试.
   *
   * @return
   * @throws IOException
   */
  @GetMapping(GETDO)
  ResponseEntity<?> getDo() throws IOException {
    return ResponseUtils.okMessage("ok");
  }

  /**
   * post请求测试.
   *
   * @param name 名称
   * @return
   * @throws IOException
   */
  @PostMapping(POSTDO)
  ResponseEntity<?> postDo(@RequestBody String name) throws IOException {
    return ResponseUtils.ok(name);
  }

  @GetMapping(GET_ERROR)
  ResponseEntity<?> getError() throws IOException {
    return ResponseUtils.badRequest("传入的参数非法！");
  }

  @GetMapping(GET_HTTP_ERROR)
  ResponseEntity<?> getHttpError() throws IOException {
    return ResponseEntity.badRequest().build();
  }

  @PostMapping(POST_DATA)
  ResponseEntity<?> postData(@RequestBody Map<String, Object> map) throws IOException {
    if (MapUtils.getString(map, "name").equals("zzl")) {
      return ResponseUtils.ok(objectMapper.writeValueAsString(map));
    } else if (MapUtils.getString(map, "name").equals("zhz")) {
      return ResponseUtils.badRequest("人员需要是本人，不能是家属");
    }
    throw Exceptions.badRequestParams("人员非法");
  }

}
