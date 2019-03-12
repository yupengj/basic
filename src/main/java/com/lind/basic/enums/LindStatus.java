package com.lind.basic.enums;

/**
 * lind框架系统状态.
 */
public enum LindStatus implements BaseEnum {
  INIT(1, "初始化"),
  NORMAL(2, "正常");

  private Integer code;
  private String description;

  LindStatus(Integer code, String description) {
    this.code = code;
    this.description = description;
  }

  @Override
  public Integer getCode() {
    return code;
  }

  @Override
  public String getDescription() {
    return description;
  }

}
