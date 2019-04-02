package com.lind.basic.util;

import org.junit.Test;

public class ExceptionTest {
  @Test(expected = ArithmeticException.class)
  public void innerExceptionTest() {
    try {
      int a = 0;
      int b = 1 / a;
    } catch (Exception ex) {
      throw ex;
    } finally {
      System.out.println("success end.");
    }
  }
}
