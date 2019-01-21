package com.lind.basic.entity.jpa;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据建立与更新.
 * Embeddable注解表示不会生成新的数据表，而它的属性会在其它表内部，比较容易实现代码复用.
 */
@Getter
@Setter
@Embeddable
public class Audit {

  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "updated_on")
  private LocalDateTime updatedOn;

  @Column(name = "updated_by")
  private String updatedBy;
}