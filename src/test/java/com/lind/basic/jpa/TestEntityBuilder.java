package com.lind.basic.jpa;

import com.lind.basic.entity.jpa.EntityBase;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class TestEntityBuilder extends EntityBase {
  private String title;
  private String description;

  /**
   * init .
   */
  @Builder(toBuilder = true)
  public TestEntityBuilder(Long id, LocalDateTime createdOn, LocalDateTime updatedOn,
                           String title, String description) {
    super(id, createdOn, updatedOn);
    this.title = title;
    this.description = description;
  }
}
