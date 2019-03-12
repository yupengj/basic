package com.lind.basic.controller;

import com.lind.basic.enums.LindStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private String name;
  private LindStatus status;
}
