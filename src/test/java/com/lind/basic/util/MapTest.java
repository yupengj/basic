package com.lind.basic.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;

public class MapTest {
  @Test(expected = NullPointerException.class)
  public void addAllEmpty() {
    Map<String, String> user = new HashMap<>();
    user.put("name", "zzl");
    user.putAll(null);
  }

  @Test()
  public void addAllNotEmpty() {
    Map<String, String> user = new HashMap<>();
    user.put("name", "zzl");
    Map<String, String> ext = new HashMap<>();
    if (MapUtils.isNotEmpty(ext)) {
      user.putAll(ext);
    }
  }
}
