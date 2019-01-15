package com.lind.basic.mybatis;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@ToString
public class UserInfo {
  private Long id;
  private String name;
  private String email;

  @CreatedOnFuncation
  private LocalDateTime createdOn;
  @UpdatedOnFuncation
  private LocalDateTime updatedOn;
}
