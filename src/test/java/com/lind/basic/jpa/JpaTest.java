package com.lind.basic.jpa;

import com.lind.basic.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaTest extends BaseTest {
  @Autowired
  TestEntityRepository testEntityRepository;
  @Autowired
  TestBuilderEntityRepository testBuilderEntityRepository;

  TestEntityBuilder getTestEntityBuilder() {
    TestEntityBuilder testEntityBuilder = TestEntityBuilder.builder()
        .title("lind")
        .description("lind is @builder and inherit")
        .build();
    return testEntityBuilder;
  }

  @Test
  public void updateListen() {
    TestEntity testEntity = TestEntity.builder()
        .title("test")
        .description("you are good")
        .build();
    testEntityRepository.save(testEntity);
    TestEntity old = testEntityRepository.findById(testEntity.getId()).orElse(null);
    old = old.toBuilder().description("modify@@@").build();
    testEntityRepository.save(old);
    Assert.assertNotNull(old);
  }

  /**
   * 测试：在实体使用继承时，如何使用@Builder注解.
   */
  @Test
  public void insertBuilderAndInherit() {
    TestEntityBuilder testEntityBuilder = getTestEntityBuilder();
    testBuilderEntityRepository.save(testEntityBuilder);
    TestEntityBuilder entity = testBuilderEntityRepository.findById(
        testEntityBuilder.getId()).orElse(null);
    System.out.println("userinfo:" + entity.toString());

    entity = entity.toBuilder().description("修改了").build();
    testBuilderEntityRepository.save(entity);
    System.out.println("userinfo:" + entity.toString());
  }

  @Test
  public void findByTitle() {
    TestEntityBuilder testEntityBuilder = getTestEntityBuilder();
    testBuilderEntityRepository.save(testEntityBuilder);
    Assert.assertEquals(1, testBuilderEntityRepository.findByTitle("lind").size());
  }
}
