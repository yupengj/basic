package com.lind.basic.mybatis;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@ToString
public class UserInfo {
  private Long id;
  private String name;
  private String email;
}
