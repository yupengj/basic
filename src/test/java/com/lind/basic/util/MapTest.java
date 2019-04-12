package com.lind.basic.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.springframework.util.Assert;

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

  @Test
  public void listIterator() {
    List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
    List<List<Integer>> ret = CommonUtils.split(array, 2);

    ret.forEach(o -> System.out.println(o));

  }

  @Test
  public void emptyMap() {
    Map<String, Object> test = null;
    Assert.isTrue(MapUtils.isEmpty(test), "ok");
  }
}
