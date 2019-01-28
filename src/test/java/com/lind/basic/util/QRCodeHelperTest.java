package com.lind.basic.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.junit.Test;

public class QRCodeHelperTest {

  @Test
  public void bgQrCode() throws Exception {
    QRCodeHelper.getBgImageQRcode("src/test/resources/image/bg.png", "http://www.baidu.com", 100, 100,
            "png", 10, 100, "src/test/resources/image/bg2.png");
  }

  @Test
  public void bgQrCodeByteArray() throws Exception {
    String outFilePath = "src/test/resources/image/bg2.png";
    File file = new File(outFilePath);
    FileOutputStream fos = new FileOutputStream(file);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    bos.write(QRCodeHelper.getBgImageQRcode(
            "src/test/resources/image/bg.png",
            "http://www.baidu.com",
            95, 95, "png", 548, 1068));
  }
}
