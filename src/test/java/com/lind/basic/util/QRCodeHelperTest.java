package com.lind.basic.util;

import org.junit.Test;

public class QRCodeHelperTest {

  @Test
  public void qrCode() throws Exception {
    String qrcodeImage =
        QRCodeHelper.generateQRCode(
            "http://www.cnblogs.com/lori", 300, 300, "png", "src/test/resources/1.png");
    System.out.println(qrcodeImage);
  }
}
