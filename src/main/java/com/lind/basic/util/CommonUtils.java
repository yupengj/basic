package com.lind.basic.util;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * 公用类库.
 */
public class CommonUtils {
  /**
   * md5 加密.
   *
   * @return
   */
  public static String encrypt(String str) {
    char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    try {
      byte[] btInput = str.getBytes("GBK");
      // 获得MD5摘要算法的 MessageDigest 对象
      MessageDigest mdInst = MessageDigest.getInstance("MD5");
      // 使用指定的字节更新摘要
      mdInst.update(btInput);
      // 获得密文
      byte[] md = mdInst.digest();
      // 把密文转换成十六进制的字符串形式
      int j = md.length;
      char[] strs = new char[j * 2];
      int k = 0;
      for (int i = 0; i < j; i++) {
        byte byte0 = md[i];
        strs[k++] = hexDigits[byte0 >>> 4 & 0xf];
        strs[k++] = hexDigits[byte0 & 0xf];
      }

      return new String(strs).toUpperCase();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 获取当前时间秒数.
   *
   * @return
   */
  public static String date2utcStr() {
    long miliSeconds = new Date().getTime();
    return String.valueOf(miliSeconds / 1000L);
  }

  /**
   * 根据当前字符串获取毫秒数.
   *
   * @return
   */
  public static String date2utcStr(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      Date d = sdf.parse(date);
      long miliSeconds = d.getTime();
      return String.valueOf(miliSeconds / 1000L);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return "error";
  }

  /**
   * 毫秒数转 LocalDateTime.
   *
   * @return
   */
  public static LocalDateTime instant2LocalDateTime(Long time) {
    if (time == null || time == 0) {
      return LocalDateTime.now();
    }
    Date date = new Date(time * 1000);
    Instant instant = date.toInstant();
    ZoneId zone = ZoneId.systemDefault();
    return LocalDateTime.ofInstant(instant, zone);
  }

  /**
   * 如果传入的时间为null，则默认为最小时间.
   * 1970-01-01
   *
   * @return
   */
  public static LocalDateTime ifNullToMinTime(Long epochMilli) {
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    if (epochMilli == null) {
      return LocalDateTime.parse("1970-01-01 00:00:00", df);
    }
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
  }

  /**
   * 如果传入的时间为null,则默认为最大时间.
   * 2999-01-01
   *
   * @return
   */
  public static LocalDateTime ifNullToMaxTime(Long epochMilli) {
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    if (epochMilli == null) {
      return LocalDateTime.parse("2999-01-01 00:00:00", df);
    }
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
  }

  /**
   * 默认字符串.
   *
   * @return
   */
  public static String stringNulltoDefault(String value) {
    if (value == null) {
      return "";
    }
    return value;
  }

  /**
   * 得到一个随机字符串.
   *
   * @return
   */
  public static String getRandomString() {
    return UUID.randomUUID().toString();
  }
}
