package com.lind.basic.mybatis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MybatisTest {
  @Autowired
  UserInfoMapper userInfoMapper;

  @Test
  public void insert() {
    UserInfo userInfo = UserInfo.builder()
        .name("lind")
        .email("test@sina.com")
        .build();
    userInfoMapper.insert(userInfo);
  }
}
