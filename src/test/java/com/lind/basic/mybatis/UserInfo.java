package com.lind.basic.mybatis;

import com.lind.basic.entity.mybatis.CreatedOnFuncation;
import com.lind.basic.entity.mybatis.UpdatedOnFuncation;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
  private Long id;
  private String name;
  private String email;

  @CreatedOnFuncation
  private Timestamp createdOn;
  @UpdatedOnFuncation
  private Timestamp updatedOn;
}
