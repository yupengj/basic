package com.lind.basic.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.basic.BaseTest;
import org.junit.Assert;
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
    System.out.println("userinfo:" + userInfo.toString());
  }

  @Test
  public void findList() {
    QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("name", "lind");
    Assert.assertEquals(0, userInfoMapper.selectList(queryWrapper).size());
  }

  @Test
  public void findPage() {
    QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("name", "lind");
    Assert.assertEquals(0, userInfoMapper.selectPage(
        new Page<>(1, 10),
        queryWrapper)
        .getSize());
  }
}
