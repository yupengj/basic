package com.lind.basic.util;

import org.junit.Assert;
import org.junit.Test;

public class StringTest {
  @Test
  public void substring() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("销售容器不足,");
    Assert.assertEquals("销售容器不足",
        stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1));
  }

  @Test
  public void stringTrim() {
    String str = "135 2197    29 91  ";
    Assert.assertEquals("13521972991", str.replaceAll("\\s*", ""));
  }

  @Test
  public void longEquals() {
    Long expected = 1L;
    Assert.assertTrue(expected == Long.valueOf(1));
  }
}
