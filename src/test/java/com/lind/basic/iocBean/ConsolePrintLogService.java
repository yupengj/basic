package com.lind.basic.iocBean;

public class ConsolePrintLogService implements PrintLogService {
  @Override
  public void print(String message) {
    System.out.println("ConsolePrintLogService.print:" + message);
  }
}
