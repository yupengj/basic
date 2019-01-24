package com.lind.basic.enums;

public interface BaseEnum<E extends Enum<?>, T> {

  public Integer getCode();

  public String getText();
}
