package com.lind.basic.jpa;

import com.lind.basic.BaseTest;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaTest extends BaseTest {
  @Autowired
  TestEntityRepository testEntityRepository;

  @Test
  public void updateListen() {
    TestEntity testEntity = TestEntity.builder()
        .title("test")
        .description("you are good")
        .build();
    testEntityRepository.save(testEntity);
    val old = testEntityRepository.findById(testEntity.getId()).orElse(null);
    Assert.assertNotNull(old);
  }
}
