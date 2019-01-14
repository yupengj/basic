package com.lind.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @MappedSuperclass是一个标识，不会生成这张数据表,子类的@Builder注解需要加在重写的构造方法上(包括父类的属性).
 */
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class EntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;


  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Column(name = "created_on")
  protected LocalDateTime createdOn;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Column(name = "updated_on")
  protected LocalDateTime updatedOn;

  /**
   * Sets createdAt before insert
   */
  @PrePersist
  public void setCreationDate() {
    this.createdOn = LocalDateTime.now();
    this.updatedOn = LocalDateTime.now();
  }

  /**
   * Sets updatedAt before update
   */
  @PreUpdate
  public void setChangeDate() {
    this.updatedOn = LocalDateTime.now();
  }


}
