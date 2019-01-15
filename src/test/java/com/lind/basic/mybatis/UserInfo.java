package com.lind.basic.mybatis;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class UserInfo {
  private Long id;
  private String name;
  private String email;
}
