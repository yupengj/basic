package com.lind.basic.nosql;

import static org.junit.Assert.assertEquals;

import com.lind.basic.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDbTest extends BaseTest {

  @Autowired
  MongoTemplate mongoTemplate;

  @Test
  public void add() {
    mongoTemplate.insert(TestUserModel.builder().id(1).name("zzl").build());
    assertEquals(1, mongoTemplate.findAll(TestUserModel.class).size());
  }
}
