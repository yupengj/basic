package com.lind.basic.mybatis;

import com.lind.basic.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MybatisTest extends BaseTest {
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
