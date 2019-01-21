package com.lind.basic.jpa;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TestBuilderEntityRepository extends CrudRepository<TestEntityBuilder, Long> {
  List<TestEntityBuilder> findByTitle(String title);

}
