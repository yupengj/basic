package com.lind.basic.jpa;

import com.lind.basic.BaseTest;
import java.time.LocalDateTime;
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
    val old = testEntityRepository.findById(1L)
        .orElse(TestEntity.builder().build()).getAudit()
        .getCreatedOn();
    Assert.assertNotEquals(old, LocalDateTime.now());
  }
}
