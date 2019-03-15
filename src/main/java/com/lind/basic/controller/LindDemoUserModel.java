package com.lind.basic.controller;

import com.lind.basic.entity.jpa.EntityBase;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 用户实体.
 */
@Entity
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LindDemoUserModel extends EntityBase {
  @NotBlank
  @Length(max = 50, message = "name最多为50个字符")
  private String name;
  @Email(message = "email不合法")
  private String email;
  @Range(min = 1, max = 200, message = "年纪应该在1~200之间")
  private Integer age;
}
