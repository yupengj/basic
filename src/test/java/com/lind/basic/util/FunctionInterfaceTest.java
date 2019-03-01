package com.lind.basic.util;

import org.junit.Test;

public class FunctionInterfaceTest {
  @Test
  public void testFunction() {
    Run run = new Run() {
      @Override
      public void print() {
        System.out.println("java8函数式接口!");
      }
    };
    run.print();
  }

  @Test
  public void testMethodFunction() {
    java8Fun(new Run() {
      @Override
      public void print() {
        System.out.println("类似.net里的委托!");
      }
    });
  }

  public void java8Fun(Run run) {
    System.out.println("执行java8函数式接口");
    run.print();
  }

  @FunctionalInterface
  interface Run {
    void print();
  }
}
