package com.lind.basic.util;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
      long dTime = d.getTime();
      return String.valueOf(dTime / 1000L);
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


  /**
   * 拆分集合.
   *
   * @param <T>     .
   * @param resList 要拆分的集合
   * @param count   每个集合的元素个数
   * @return 返回拆分后的各个集合
   */
  public static <T> List<List<T>> split(List<T> resList, int count) {

    if (resList == null || count < 1) {
      return null;
    }
    List<List<T>> ret = new ArrayList<>();
    int size = resList.size();
    if (size <= count) { //数据量不足count指定的大小
      ret.add(resList);
    } else {
      int pre = size / count;
      int last = size % count;
      //前面pre个集合，每个大小都是count个元素
      for (int i = 0; i < pre; i++) {
        List<T> itemList = new ArrayList<T>();
        for (int j = 0; j < count; j++) {
          itemList.add(resList.get(i * count + j));
        }
        ret.add(itemList);
      }
      //last的进行处理
      if (last > 0) {
        List<T> itemList = new ArrayList<T>();
        for (int i = 0; i < last; i++) {
          itemList.add(resList.get(pre * count + i));
        }
        ret.add(itemList);
      }
    }
    return ret;

  }
}
