package com.lind.basic.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lind.basic.enums.BaseEnum;
import org.junit.Assert;
import org.junit.Test;

enum EnumPayTaxesNature implements BaseEnum {

  OTHER(0, "其他"), SMALL_SCALE(1, "小规模"), GENERALLY(2, "一般人");
  private Integer code;
  private String text;

  EnumPayTaxesNature(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  @JsonCreator
  public static EnumPayTaxesNature getPayTaxesNature(String code) {
    return EnumUtils.codeOf(EnumPayTaxesNature.class, Integer.parseInt(code));
  }

  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getText() {
    return this.text;
  }

  @JsonValue
  public String getCodeStr() {
    return this.code + "";
  }
}

public class EnumTest {

  @Test
  public void codeTest() {
    Assert.assertEquals("SMALL_SCALE", EnumPayTaxesNature.getPayTaxesNature("1").name());
  }

  @Test
  public void TextTest() {
    Assert.assertEquals("小规模", EnumPayTaxesNature.getPayTaxesNature("1").getText());
  }
}
